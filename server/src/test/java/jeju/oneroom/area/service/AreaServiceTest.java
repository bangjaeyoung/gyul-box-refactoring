package jeju.oneroom.area.service;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.exception.BusinessLogicException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class AreaServiceTest {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaService areaService;

    @AfterEach
    void tearDown() {
        areaRepository.deleteAllInBatch();
    }

    @DisplayName("지역 이름으로 지역을 조회한다.")
    @Test
    void findAreaByAreaName_Exists() {
        // given
        Area area1 = createArea(1L, "가나읍");
        Area area2 = createArea(2L, "다라읍");
        Area area3 = createArea(3L, "마바읍");
        areaRepository.saveAll(List.of(area1, area2, area3));

        // when
        AreaDto.Response result = areaService.findAreaByAreaName("다라읍");

        // then
        assertThat(result).isNotNull();
        assertThat(result).extracting("areaCode", "areaName")
            .contains(2L, "다라읍");
    }

    @DisplayName("지역 이름으로 지역을 조회한다. 존재하지 않을 경우, 예외가 발생한다.")
    @Test
    void findAreaByAreaName() {
        // given
        Area area1 = createArea(1L, "가나읍");
        Area area2 = createArea(2L, "다라읍");
        Area area3 = createArea(3L, "마바읍");
        areaRepository.saveAll(List.of(area1, area2, area3));

        String notExistedAreaName = "자차읍";

        // when // then
        assertThatThrownBy(() -> areaService.findAreaByAreaName(notExistedAreaName))
            .isInstanceOf(BusinessLogicException.class)
            .extracting("exceptionCode.status", "exceptionCode.message")
            .contains(404, "Area not found");
    }

    @DisplayName("지역 코드로 지역 조회 시, 해당 지역이 존재할 경우 지역을 정상적으로 반환한다.")
    @Test
    void findVerifiedAreaByAreaCode_Exists() {
        // given
        Area area1 = createArea(1L, "가나읍");
        Area area2 = createArea(2L, "다라읍");
        Area area3 = createArea(3L, "마바읍");
        areaRepository.saveAll(List.of(area1, area2, area3));

        // when
        Area result = areaService.findVerifiedAreaByAreaCode(2L);

        // then
        assertThat(result).isNotNull();
        assertThat(result).extracting("areaCode", "areaName")
            .contains(2L, "다라읍");
    }

    @DisplayName("지역 코드로 지역 조회 시, 해당 지역이 존재하지 않을 경우 에러를 발생시킨다.")
    @Test
    void findVerifiedAreaByAreaCodeExists_NotFound() {
        // given
        Area area1 = createArea(1L, "가나읍");
        Area area2 = createArea(2L, "다라읍");
        Area area3 = createArea(3L, "마바읍");
        areaRepository.saveAll(List.of(area1, area2, area3));

        long notExistedAreaCode = 4L;

        // when // then
        assertThatThrownBy(() -> areaService.findVerifiedAreaByAreaCode(notExistedAreaCode))
            .isInstanceOf(BusinessLogicException.class)
            .extracting("exceptionCode.status", "exceptionCode.message")
            .contains(404, "Area not found");

//        BusinessLogicException exception = assertThrows(BusinessLogicException.class,
//            () -> areaService.findVerifiedAreaByAreaCode(notExistedAreaCode));
//
//        assertThat(exception.getExceptionCode().getStatus()).isEqualTo(404);
//        assertThat(exception.getExceptionCode().getMessage()).isEqualTo("Area not found");
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
