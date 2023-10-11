package jeju.oneroom.domain.area.mapper;

import jeju.oneroom.domain.area.dto.AreaDto;
import jeju.oneroom.domain.area.entity.Area;
import jeju.oneroom.domain.houseInfo.mapper.HouseInfoMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {HouseInfoMapper.class})
public interface AreaMapper {
    AreaDto.Response areaToResponseDto(Area area);
}
