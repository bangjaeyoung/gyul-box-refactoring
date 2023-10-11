package jeju.oneroom.domain.area.entity;

import jeju.oneroom.global.common.entity.BaseEntity;
import jeju.oneroom.global.common.entity.Coordinate;
import jeju.oneroom.domain.houseInfo.entity.HouseInfo;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Area extends BaseEntity {

    // GeneratedValue 사용하지 않고 동면읍 코드를 Id로 사용
    @Id
    @Column(name = "area_id")
    private Long areaCode;

    private String areaName;

    @OneToMany(mappedBy = "area")
    private List<HouseInfo> houseInfos = new ArrayList<>();

    @Embedded
    private Coordinate coordinate;

    @Builder
    private Area(Long areaCode, String areaName, List<HouseInfo> houseInfos, Coordinate coordinate) {
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.houseInfos = houseInfos;
        this.coordinate = coordinate;
    }
}
