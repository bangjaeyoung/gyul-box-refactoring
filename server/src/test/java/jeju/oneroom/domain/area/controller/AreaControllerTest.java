package jeju.oneroom.domain.area.controller;

import jeju.oneroom.domain.area.controller.AreaController;
import jeju.oneroom.domain.area.dto.AreaDto;
import jeju.oneroom.domain.area.entity.Area;
import jeju.oneroom.domain.area.service.AreaService;
import jeju.oneroom.global.common.entity.Coordinate;
import jeju.oneroom.global.exception.BusinessLogicException;
import jeju.oneroom.global.exception.ExceptionCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = AreaController.class)
class AreaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AreaService areaService;

    @DisplayName("지역 이름을 통해 지역을 조회 시, 해당 지역이 존재할 경우 200 코드와 일치하는 데이터를 반환한다.")
    @Test
    void searchAreaByAreaName_Exists() throws Exception {
        // given
        String existedAreaName = "가나읍";

        AreaDto.Response expectedResponseDto = AreaDto.Response.builder()
            .areaCode(11L)
            .areaName(existedAreaName)
            .coordinate(null)
            .houseInfos(null)
            .build();

        when(areaService.findAreaByAreaName(existedAreaName)).thenReturn(expectedResponseDto);

        // when // then
        mockMvc.perform(
                get("/areas/search")
                    .queryParam("name", existedAreaName)
                    .contentType(APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.areaName").value(existedAreaName));
    }

    @DisplayName("지역 이름을 통해 지역을 조회 시, 해당 지역이 없을 경우 404 코드와 에러 메시지를 포함하는 Body를 반환한다.")
    @Test
    void searchAreaByAreaName_NotFound() throws Exception {
        // given
        String notExistedAreaName = "다라읍";

        when(areaService.findAreaByAreaName(notExistedAreaName)).thenThrow(new BusinessLogicException(ExceptionCode.NOT_FOUND_AREA));

        // when // then
        mockMvc.perform(
                get("/areas/search")
                    .queryParam("name", notExistedAreaName)
                    .contentType(APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value("404"))
            .andExpect(jsonPath("$.message").value("Area not found"));
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
