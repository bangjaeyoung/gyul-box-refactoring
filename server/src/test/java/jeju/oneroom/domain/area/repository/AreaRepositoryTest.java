package jeju.oneroom.domain.area.repository;

import jeju.oneroom.domain.area.entity.Area;
import jeju.oneroom.global.common.entity.Coordinate;
import jeju.oneroom.domain.area.repository.AreaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AreaRepositoryTest {

    @Autowired
    private AreaRepository areaRepository;

    @DisplayName("지역 이름을 통해 단일 지역을 조회한다. 존재한다면 지역을 반환한다.")
    @Test
    void findByAreaName_Exists() {
        // given
        Coordinate coordinate = createCoordinate();

        Area area = createArea(1L, "가나읍");

        areaRepository.save(area);

        // when
        Optional<Area> result = areaRepository.findByAreaName("가나읍");

        // then
        assertThat(result).isPresent();
        assertThat(result.get().getAreaName()).isEqualTo("가나읍");
    }

    @DisplayName("지역 이름을 통해 단일 지역을 조회한다. 존재하지 않는다면, 빈 객체가 반환된다.")
    @Test
    void findByAreaName_NotFound() {
        // given
        Coordinate coordinate = createCoordinate();

        Area area = createArea(1L, "가나읍");

        areaRepository.save(area);

        // when
        Optional<Area> result = areaRepository.findByAreaName("가나");

        // then
        assertThat(result).isEmpty();
    }

    private Area createArea(Long areaCode, String areaName) {
        return Area.builder()
            .areaCode(areaCode)
            .areaName(areaName)
            .coordinate(createCoordinate())
            .build();
    }

    private Coordinate createCoordinate() {
        return Coordinate.builder()
            .latitude(11.1111)
            .longitude(22.2222)
            .build();
    }
}
