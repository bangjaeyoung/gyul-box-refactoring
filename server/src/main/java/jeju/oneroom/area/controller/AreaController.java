package jeju.oneroom.area.controller;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.service.AreaService;
import jeju.oneroom.common.dto.ListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping("/areas/search")
    public ResponseEntity<ListResponseDto<AreaDto.Response>> getArea(@RequestParam("name") @NotBlank String areaName) {
        List<AreaDto.Response> areasByAreaName = areaService.findAreasByAreaName(areaName);

        return new ResponseEntity<>(new ListResponseDto<>(areasByAreaName), HttpStatus.OK);
    }
}
