package jeju.oneroom.domain.user.mapper;

import jeju.oneroom.domain.user.dto.UserDto;
import jeju.oneroom.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "areaName", source = "area.areaName")
    @Mapping(target = "profileImageUrl", ignore = true)
    UserDto.Response userToResponseDto(User user);

    UserDto.SimpleResponse userToSimpleResponseDto(User user);
}
