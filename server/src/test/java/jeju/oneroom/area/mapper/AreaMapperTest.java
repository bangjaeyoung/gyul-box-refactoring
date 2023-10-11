package jeju.oneroom.area.mapper;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.common.entity.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class AreaMapperTest {

    @Autowired
    private AreaMapper areaMapper;

    @DisplayName("Area Entity가 Area ResponseDto로 정상적으로 매핑되는지 확인한다.")
    @Test
    void areaToResponseDto() {
        // given
        Coordinate coordinate = Coordinate.builder()
            .latitude(11.1111)
            .longitude(22.2222)
            .build();

        Area area = Area.builder()
            .areaCode(11L)
            .areaName("가나읍")
            .coordinate(coordinate)
            .build();

        // when
        AreaDto.Response areaResponseDto = areaMapper.areaToResponseDto(area);

        // then
        assertThat(areaResponseDto).extracting("areaCode", "areaName", "coordinate.latitude", "coordinate.longitude", "houseInfos")
            .containsExactlyInAnyOrder(11L, "가나읍", 11.1111, 22.2222, null);
    }
}

