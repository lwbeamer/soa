package org.itmo.spacemarine.controller;


import lombok.RequiredArgsConstructor;
import org.itmo.spacemarine.dto.SpaceMarineResponseDto;
import org.itmo.spacemarine.exception.BusinessException;
import org.itmo.spacemarine.exception.ExceptionCode;
import org.itmo.spacemarine.service.SpaceMarineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/space-utils")
public class SpaceUtilsController {

    private final SpaceMarineService spaceMarineService;


    @GetMapping("/{field-name}/min")
    public SpaceMarineResponseDto getWithMinField(@PathVariable(name = "field-name") String field) {
        return spaceMarineService.getWithMinField(field);
    }

    @GetMapping("/{field-name}/max")
    public SpaceMarineResponseDto getWithMaxField(@PathVariable(name = "field-name") String field) {
        return spaceMarineService.getWithMaxField(field);
    }

    @GetMapping("/{field-name}/sum")
    public ResponseEntity<?> getSumByField(@PathVariable(name = "field-name") String field) {
        if (!field.equals("health")) throw new BusinessException(ExceptionCode.InvalidRequest,
                "Сумма может быть применима только к полю halth");

        Long sum = spaceMarineService.getHealthSum();
        Map<Object, Object> response = new HashMap<>();
        response.put("sumHealth", sum);
        return ResponseEntity.ok(response);
    }
}
