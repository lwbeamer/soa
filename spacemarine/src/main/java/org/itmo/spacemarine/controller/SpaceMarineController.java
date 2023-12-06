package org.itmo.spacemarine.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.spacemarine.dto.SpaceMarineCreateRequestDto;
import org.itmo.spacemarine.dto.SpaceMarineResponseDto;
import org.itmo.spacemarine.dto.SpaceMarineUpdateDto;
import org.itmo.spacemarine.entity.SpaceMarine;
import org.itmo.spacemarine.exception.BusinessException;
import org.itmo.spacemarine.exception.ExceptionCode;
import org.itmo.spacemarine.service.SpaceMarineService;
import org.itmo.spacemarine.util.FieldsValidator;
import org.itmo.spacemarine.util.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RequiredArgsConstructor
@RestController
@RequestMapping("/space-marines")
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
public class SpaceMarineController {

    private final SpaceMarineService spaceMarineService;

    private final FieldsValidator fieldsValidator;

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
    public ResponseEntity<?> updateSpaceMarine(@PathVariable String id, @RequestBody SpaceMarineUpdateDto requestDto){
        long longId;
        try{
            longId = Long.parseLong(id);
        } catch (NumberFormatException ex){
            throw new BusinessException(ExceptionCode.InvalidRequest, "Некорректрые параметры запроса!");
        }

        fieldsValidator.validateUpdateRequest(requestDto);
        spaceMarineService.updateSpaceMarine(longId, requestDto);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
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
        fieldsValidator.validateCreateRequest(requestDto);
        spaceMarineService.addNewMarine(requestDto);
    }
}
