package jeju.oneroom.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jeju.oneroom.domain.area.entity.Area;
import jeju.oneroom.domain.area.entity.QArea;
import jeju.oneroom.domain.houseinfo.entity.HouseInfo;
import jeju.oneroom.domain.houseinfo.entity.QHouseInfo;
import jeju.oneroom.domain.review.entity.QReview;
import jeju.oneroom.domain.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static jeju.oneroom.domain.houseinfo.entity.QHouseInfo.houseInfo;
import static jeju.oneroom.domain.review.entity.QReview.review;
import static jeju.oneroom.domain.user.entity.QUser.user;


@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<Review> findById(long reviewId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(review)
                .leftJoin(review.user, user).fetchJoin()
                .where(review.id.eq(reviewId))
                .fetchOne());
    }

    @Override
    public List<Review> findTop5ByHouseInfo_Area(Area area) {
        return jpaQueryFactory.selectFrom(review)
                .leftJoin(review.houseInfo,houseInfo).fetchJoin()
                .leftJoin(houseInfo.area, QArea.area).fetchJoin()
                .where(review.houseInfo.area.eq(area))
                .orderBy(review.likes.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public Page<Review> findByHouseInfo_Area(Area area, Pageable pageable) {
        List<Review> reviews = getReviewByArea(area, pageable);

        Long count = jpaQueryFactory.select(review.count())
                .from(review)
                .where(review.houseInfo.area.eq(area))
                .fetchOne();

        return new PageImpl<>(reviews, pageable, count);
    }

    private List<Review> getReviewByArea(Area area,Pageable pageable) {
        return jpaQueryFactory.selectFrom(review)
                .leftJoin(review.houseInfo, houseInfo).fetchJoin()
                .leftJoin(houseInfo.area, QArea.area).fetchJoin()
                .where(review.houseInfo.area.eq(area))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Page<Review> findByHouseInfo(HouseInfo houseInfo, Pageable pageable) {
        List<Review> reviews = getReviewsByHouseInfo(houseInfo, pageable);

        Long count = jpaQueryFactory.select(review.count())
                .from(review)
                .where(review.houseInfo.eq(houseInfo))
                .fetchOne();

        return new PageImpl<>(reviews, pageable, count);
    }

    private List<Review> getReviewsByHouseInfo(HouseInfo houseInfo, Pageable pageable) {
        return jpaQueryFactory.selectFrom(review)
                .leftJoin(review.houseInfo, QHouseInfo.houseInfo).fetchJoin()
                .where(review.houseInfo.eq(houseInfo))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
