package jeju.oneroom.area.service;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.entity.Area;
import jeju.oneroom.area.mapper.AreaMapper;
import jeju.oneroom.area.repository.AreaRepository;
import jeju.oneroom.exception.BusinessLogicException;
import jeju.oneroom.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AreaService {

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;

    public AreaDto.Response findAreaByAreaName(String areaName) {
        Area area = areaRepository.findByAreaName(areaName);

        return areaMapper.areaToResponseDto(area);
    }

    public Area findVerifiedAreaByAreaCode(long areaCode) {
        return areaRepository.findById(areaCode)
            .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND_AREA));
    }
}
