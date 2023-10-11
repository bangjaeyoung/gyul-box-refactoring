package jeju.oneroom.domain.post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jeju.oneroom.domain.houseinfo.entity.HouseInfo;
import jeju.oneroom.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static jeju.oneroom.domain.houseinfo.entity.QHouseInfo.houseInfo;
import static jeju.oneroom.domain.post.entity.QPost.post;
import static jeju.oneroom.domain.postcomment.entity.QPostComment.postComment;
import static jeju.oneroom.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class PostCustomRepositoryImpl implements PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Post> findPostById(long postId) {
        Post post1 = jpaQueryFactory.selectFrom(post)
            .leftJoin(post.user, user).fetchJoin()
            .leftJoin(post.houseInfo, houseInfo).fetchJoin()
            .leftJoin(post.postComments, postComment).fetchJoin()
            .leftJoin(postComment.user, user).fetchJoin()
            .where(post.id.eq(postId))
            .fetchOne();

        return Optional.ofNullable(post1);
    }

    @Override
    public Page<Post> findPostsByHouseInfoIn(List<HouseInfo> houseInfos, Pageable pageable) {
        List<Post> posts = getPostsByHouseInfoIn(houseInfos, pageable);

        Long count = jpaQueryFactory.select(post.count())
            .from(post)
            .where(post.houseInfo.in(houseInfos))
            .fetchOne();

        return new PageImpl<>(posts, pageable, count);
    }

    private List<Post> getPostsByHouseInfoIn(List<HouseInfo> houseInfos, Pageable pageable) {
        return jpaQueryFactory.selectFrom(post)
            .leftJoin(post.user, user).fetchJoin()
            .where(post.houseInfo.in(houseInfos))
            .orderBy(post.modifiedAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    @Override
    public Page<Post> findPostsByTitleContains(String title, Pageable pageable) {
        List<Post> posts = getPostsByTitleContains(title, pageable);

        Long count = jpaQueryFactory.select(post.count())
            .from(post)
            .where(post.title.toUpperCase().contains(title.toUpperCase()))
            .fetchOne();

        return new PageImpl<>(posts, pageable, count);
    }

    private List<Post> getPostsByTitleContains(String title, Pageable pageable) {
        return jpaQueryFactory.selectFrom(post)
            .leftJoin(post.user, user).fetchJoin()
            .where(post.title.toUpperCase().contains(title.toUpperCase()))
            .orderBy(post.modifiedAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    @Override
    public Page<Post> findAllPosts(Pageable pageable) {
        List<Post> posts = getAllPosts(pageable);

        Long count = jpaQueryFactory.select(post.count())
            .from(post)
            .fetchOne();

        return new PageImpl<>(posts, pageable, count);
    }

    private List<Post> getAllPosts(Pageable pageable) {
        return jpaQueryFactory.selectFrom(post)
            .leftJoin(post.user, user).fetchJoin()
            .orderBy(post.modifiedAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }
}
