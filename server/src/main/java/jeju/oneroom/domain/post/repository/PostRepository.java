package jeju.oneroom.domain.post.repository;

import jeju.oneroom.domain.post.entity.Post;
import jeju.oneroom.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

    // 단일 사용자의 모든 게시글 조회
    Page<Post> findAllByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
