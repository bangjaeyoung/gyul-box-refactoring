package jeju.oneroom.domain.reviewlike.service;

import jeju.oneroom.domain.review.entity.Review;
import jeju.oneroom.domain.reviewlike.repository.ReviewLikeRepository;
import jeju.oneroom.global.exception.BusinessLogicException;
import jeju.oneroom.global.exception.ExceptionCode;
import jeju.oneroom.domain.reviewlike.entity.ReviewLike;
import jeju.oneroom.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewLikeService {
    private final ReviewLikeRepository reviewLikeRepository;

    // 좋아요 및 취소
    // 좋아요를 누르지 않은 유저는 좋아요 추가, 좋아요를 누른 유저는 좋아요 삭제
    @Transactional
    public void pushLike(Review review, User user) {
        checkSameUser(review, user);
        reviewLikeRepository.findByReviewAndUser(review, user)
                .ifPresentOrElse(
                        reviewLike -> reviewLikeRepository.deleteById(reviewLike.getId()),
                        () -> {
                            ReviewLike reviewLike = ReviewLike.builder().review(review).user(user).build();
                            reviewLikeRepository.save(reviewLike);
                        });
    }

    // 리뷰 작성자가 좋아요를 누를 경우 방지
    private void checkSameUser(Review review, User user) {
        if (review.getUser() == user) {
            throw new BusinessLogicException(ExceptionCode.NO_PERMISSION_TO_LIKE);
        }
    }
}

