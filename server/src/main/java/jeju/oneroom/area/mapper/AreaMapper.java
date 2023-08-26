package jeju.oneroom.area.mapper;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.houseInfo.mapper.HouseInfoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {HouseInfoMapper.class})
public interface AreaMapper {
    AreaDto.Response areaToResponseDto(Area area);
}
