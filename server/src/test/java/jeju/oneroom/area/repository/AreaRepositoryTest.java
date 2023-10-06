package jeju.oneroom.area.repository;

import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class AreaRepositoryTest {

    @Autowired
    private AreaRepository areaRepository;

    @DisplayName("지역 이름을 통해 단일 지역을 조회한다.")
    @Test
    void findByAreaName() {
        // given
        Coordinate coordinate = Coordinate.builder()
            .latitude(11.1111)
            .longitude(22.2222)
            .build();

        Area area = Area.builder()
            .areaCode(1L)
            .areaName("가나읍")
            .coordinate(coordinate)
            .build();

        areaRepository.save(area);

        // when
        Area foundArea = areaRepository.findByAreaName("가나읍");

        // then
        assertThat(foundArea.getAreaName()).isEqualTo("가나읍");
    }
}
