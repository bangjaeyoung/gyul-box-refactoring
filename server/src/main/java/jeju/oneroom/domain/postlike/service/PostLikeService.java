package jeju.oneroom.domain.postlike.service;

import jeju.oneroom.global.exception.BusinessLogicException;
import jeju.oneroom.global.exception.ExceptionCode;
import jeju.oneroom.domain.post.entity.Post;
import jeju.oneroom.domain.postlike.entity.PostLike;
import jeju.oneroom.domain.postlike.repository.PostLikeRepository;
import jeju.oneroom.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;

    // 좋아요 및 취소
    // 좋아요를 누르지 않은 유저는 좋아요 추가, 좋아요를 누른 유저는 좋아요 삭제
    @Transactional
    public void pushLike(Post post, User user) {
        checkSameUser(post, user);
        postLikeRepository.findByPostAndUser(post, user)
                .ifPresentOrElse(
                        postLike -> postLikeRepository.deleteById(postLike.getId()),
                        () -> {
                            PostLike postLike = PostLike.builder().post(post).user(user).build();
                            postLikeRepository.save(postLike);
                        });
    }

    // 동일한 게시글 작성자가 좋아요를 누를 경우 방지
    private void checkSameUser(Post post, User user) {
        if (post.getUser() == user) {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_LIKE);
        }
    }
}

