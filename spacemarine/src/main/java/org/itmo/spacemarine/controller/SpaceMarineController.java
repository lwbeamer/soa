package org.itmo.spacemarine.controller;

import lombok.RequiredArgsConstructor;
import org.itmo.spacemarine.dto.SpaceMarineCreateRequestDto;
import org.itmo.spacemarine.dto.SpaceMarineResponseDto;
import org.itmo.spacemarine.service.SpaceMarineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/space-marines")
public class SpaceMarineController {

    private final SpaceMarineService spaceMarineService;


    @GetMapping
    public void getAll() {
        System.out.println("get method invocation!!");
    }

    @GetMapping("/{id}")
    public SpaceMarineResponseDto getById(@PathVariable Long id) {
        return spaceMarineService.getById(id);
    }

    @PostMapping
    public void addSpaceMarine(@RequestBody SpaceMarineCreateRequestDto requestDto) {
        System.out.println("ПОСТУПИЛ ТАКОЙ ЗАПРОС: " + requestDto);
        spaceMarineService.addNewMarine(requestDto);
    }
}
