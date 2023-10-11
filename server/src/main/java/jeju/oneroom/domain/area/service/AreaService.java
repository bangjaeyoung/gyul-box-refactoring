package jeju.oneroom.domain.area.service;

import jeju.oneroom.domain.area.dto.AreaDto;
import jeju.oneroom.domain.area.entity.Area;
import jeju.oneroom.domain.area.mapper.AreaMapper;
import jeju.oneroom.domain.area.repository.AreaRepository;
import jeju.oneroom.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static jeju.oneroom.global.exception.ExceptionCode.NOT_FOUND_AREA;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AreaService {

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;

    // 지역 이름을 통한 지역 조회
    public AreaDto.Response findAreaByAreaName(String areaName) {
        Area area = findVerifiedAreaByAreaName(areaName);

        return areaMapper.areaToResponseDto(area);
    }

    // 지역 코드를 통한 지역 조회 시, 존재하지 않을 경우 예외 발생
    public Area findVerifiedAreaByAreaCode(long areaCode) {
        return areaRepository.findById(areaCode)
            .orElseThrow(() -> new BusinessLogicException(NOT_FOUND_AREA));
    }

    // 지역 이름을 통한 지역 조회 시, 존재하지 않을 경우 예외 발생
    private Area findVerifiedAreaByAreaName(String areaName) {
        return areaRepository.findByAreaName(areaName)
            .orElseThrow(() -> new BusinessLogicException(NOT_FOUND_AREA));
    }
}
