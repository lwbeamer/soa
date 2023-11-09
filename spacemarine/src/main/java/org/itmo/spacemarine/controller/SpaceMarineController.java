package org.itmo.spacemarine.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.spacemarine.dto.SpaceMarineCreateRequestDto;
import org.itmo.spacemarine.dto.SpaceMarineResponseDto;
import org.itmo.spacemarine.dto.SpaceMarineUpdateDto;
import org.itmo.spacemarine.entity.SpaceMarine;
import org.itmo.spacemarine.exception.BusinessException;
import org.itmo.spacemarine.exception.ExceptionCode;
import org.itmo.spacemarine.service.SpaceMarineService;
import org.itmo.spacemarine.util.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
@RequiredArgsConstructor
@RestController
@RequestMapping("/space-marines")
public class SpaceMarineController {

    private final SpaceMarineService spaceMarineService;

    @GetMapping
    public Page<SpaceMarine> getAll(HttpServletRequest request) {
        return spaceMarineService.getAll(request);
    }

    @GetMapping("/{id}")
    public SpaceMarineResponseDto getById(@PathVariable String id) {
        long longId;
        try{
            longId = Long.parseLong(id);
        } catch (NumberFormatException ex){
            throw new BusinessException(ExceptionCode.InvalidRequest, "Некорректрые параметры запроса!");
        }
        return spaceMarineService.getById(longId);
    }


    @PutMapping("/{id}")
    public void updateSpaceMarine(@PathVariable String id, @RequestBody SpaceMarineUpdateDto requestDto){
        long longId;
        try{
            longId = Long.parseLong(id);
        } catch (NumberFormatException ex){
            throw new BusinessException(ExceptionCode.InvalidRequest, "Некорректрые параметры запроса!");
        }
        spaceMarineService.updateSpaceMarine(longId, requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSpaceMarine(@PathVariable String id) {
        long longId;
        try{
            longId = Long.parseLong(id);
        } catch (NumberFormatException ex){
            throw new BusinessException(ExceptionCode.InvalidRequest, "Некорректрые параметры запроса!");
        }
        spaceMarineService.deleteSpaceMarine(longId);
    }

    @PostMapping
    public void addSpaceMarine(@RequestBody SpaceMarineCreateRequestDto requestDto) {
        spaceMarineService.addNewMarine(requestDto);
    }
}
