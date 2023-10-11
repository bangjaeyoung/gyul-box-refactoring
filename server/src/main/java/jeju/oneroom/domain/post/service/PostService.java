package jeju.oneroom.domain.post.service;

import jeju.oneroom.domain.post.mapper.PostMapper;
import jeju.oneroom.domain.post.repository.PostRepository;
import jeju.oneroom.global.exception.BusinessLogicException;
import jeju.oneroom.global.exception.ExceptionCode;
import jeju.oneroom.domain.houseinfo.entity.HouseInfo;
import jeju.oneroom.domain.post.dto.PostDto;
import jeju.oneroom.domain.post.entity.Post;
import jeju.oneroom.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;

    // 게시글 생성
    @Transactional
    public Post createPost(PostDto.Post postDto, HouseInfo houseInfo, User user) {
        Post post = postMapper.postDtoToPost(postDto);
        post.setProperties(houseInfo, user);

        return postRepository.save(post);
    }

    // 게시글 수정
    @Transactional
    public Post updatePost(User user, PostDto.Patch patchDto) {
        Post verifiedPost = findVerifiedPost(patchDto.getPostId());

        if (verifiedPost.isAuthor(user)) {
            verifiedPost.update(patchDto.getTitle(), patchDto.getContent());
        } else {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_EDIT);
        }

        return verifiedPost;
    }

    // 게시글 id로 단일 게시글 조회
    @Transactional
    public PostDto.Response findPostByPostId(long postId) {
        Post verifiedPost = findVerifiedPost(postId);
        verifiedPost.increaseViews();

        return postMapper.postToResponseDto(verifiedPost);
    }

    // 유저의 모든 게시글 최신순으로 조회
    public Page<PostDto.SimpleResponseDto> findPostsByUserId(User user, int page, int size) {
        return postRepository.findAllByUserOrderByCreatedAtDesc(user, PageRequest.of(page - 1, size))
            .map(postMapper::postToSimpleResponseDto);
    }

    // 제목을 통한 다중 게시글 최신순으로 조회
    public Page<PostDto.SimpleResponseDto> findPostsByTitle(String title, int page, int size) {
        return postRepository.findPostsByTitleContains(title, PageRequest.of(page - 1, size))
            .map(postMapper::postToSimpleResponseDto);
    }

    // 모든 게시글 최신순으로 조회
    public Page<PostDto.SimpleResponseDto> findAllPost(int page, int size) {
        return postRepository.findAllPosts(PageRequest.of(page - 1, size))
            .map(postMapper::postToSimpleResponseDto);
    }

    // 다수의 건물에 속한 다중 게시글 조회
    public Page<PostDto.SimpleResponseDto> findPostsByHouseInfos(List<HouseInfo> houseInfos, int page, int size) {
        return postRepository.findPostsByHouseInfoIn(houseInfos, PageRequest.of(page - 1, size))
            .map(postMapper::postToSimpleResponseDto);
    }

    // 게시글 id로 단일 게시글 삭제
    @Transactional
    public void deletePost(User user, long postId) {
        Post verifiedPost = findVerifiedPost(postId);

        if (verifiedPost.isAuthor(user)) {
            postRepository.deleteById(postId);
        } else {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_DELETE);
        }
    }

    // 게시글 id로 존재 여부 검증
    @Transactional
    public Post findVerifiedPost(long postId) {
        return postRepository.findPostById(postId)
            .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_POST));
    }
}
