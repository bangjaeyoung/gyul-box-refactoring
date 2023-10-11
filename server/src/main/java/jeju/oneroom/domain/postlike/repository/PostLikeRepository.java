package jeju.oneroom.domain.postlike.repository;

import jeju.oneroom.domain.postlike.entity.PostLike;
import jeju.oneroom.domain.post.entity.Post;
import jeju.oneroom.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    // 해당 게시글에 해당 유저의 좋아요 가져오기
    Optional<PostLike> findByPostAndUser(Post post, User user);
}
