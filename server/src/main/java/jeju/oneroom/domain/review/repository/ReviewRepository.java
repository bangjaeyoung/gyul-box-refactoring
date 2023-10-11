package jeju.oneroom.domain.review.repository;

import jeju.oneroom.domain.review.entity.Review;
import jeju.oneroom.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> , ReviewCustomRepository{
    // 유저가 작성 한 리뷰
    Page<Review> findByUser(User user, Pageable pageable);

    // 좋아요 순 리뷰 2개
    List<Review> findTop2ByOrderByLikesDesc();
}
