package jeju.oneroom.global.common.entity;

import lombok.*;

import javax.persistence.Embeddable;

/**
 * Area, HouseInfo 엔티티에 필요한 지역에 대한 위도와 경도 데이터
 */
@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coordinate {

    private Double latitude;
    private Double longitude;

    @Builder
    private Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
