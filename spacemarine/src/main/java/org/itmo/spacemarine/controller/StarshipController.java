package org.itmo.spacemarine.controller;


import lombok.RequiredArgsConstructor;
import org.itmo.spacemarine.dto.StarshipCreateRequestDto;
import org.itmo.spacemarine.entity.Starship;
import org.itmo.spacemarine.exception.BusinessException;
import org.itmo.spacemarine.exception.ExceptionCode;
import org.itmo.spacemarine.service.StarshipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "*")
@RestController
@RequestMapping("/starships")
public class StarshipController {

    private final StarshipService starshipService;

    @GetMapping
    public List<Starship> getAll() {
        return starshipService.getAll();
    }

    @GetMapping("/{id}")
    public Starship getById(@PathVariable String id) {
        long longId;
        try{
            longId = Long.parseLong(id);
        } catch (NumberFormatException ex){
            throw new BusinessException(ExceptionCode.InvalidRequest, "Некорректрые параметры запроса!");
        }
        return starshipService.getById(longId);
    }

    @DeleteMapping("/{id}")
    public void deleteStarship(@PathVariable String id) {
        long longId;
        try{
            longId = Long.parseLong(id);
        } catch (NumberFormatException ex){
            throw new BusinessException(ExceptionCode.InvalidRequest, "Некорректрые параметры запроса!");
        }
        starshipService.deleteStarship(longId);
    }

    @PostMapping
    public void addStarship(@RequestBody StarshipCreateRequestDto requestDto) {
        starshipService.addStarship(requestDto);
    }

}
