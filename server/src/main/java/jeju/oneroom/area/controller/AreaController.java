package jeju.oneroom.area.controller;

import jeju.oneroom.area.dto.AreaDto;
import jeju.oneroom.area.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping("/areas/search")
    public ResponseEntity<AreaDto.Response> searchAreaByAreaName(@RequestParam("name") @NotBlank String areaName) {
        AreaDto.Response response = areaService.findAreaByAreaName(areaName);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
