package jeju.oneroom.area.dto;

import jeju.oneroom.common.entity.Coordinate;
import jeju.oneroom.houseInfo.dto.HouseInfoDto;
import lombok.*;

import java.util.List;

public class AreaDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long areaCode;
        private String areaName;
        private Coordinate coordinate;
        private List<HouseInfoDto.SimpleResponse> houseInfos;
    }
}
