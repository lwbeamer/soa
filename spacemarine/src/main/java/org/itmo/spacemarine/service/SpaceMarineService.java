package org.itmo.spacemarine.service;


import lombok.RequiredArgsConstructor;
import org.itmo.spacemarine.converter.SpaceMarineConverter;
import org.itmo.spacemarine.dto.SpaceMarineCreateRequestDto;
import org.itmo.spacemarine.dto.SpaceMarineResponseDto;
import org.itmo.spacemarine.entity.SpaceMarine;
import org.itmo.spacemarine.exception.EntityNotFoundException;
import org.itmo.spacemarine.repository.SpaceMarineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class SpaceMarineService {


    private final SpaceMarineRepository spaceMarineRepository;

    private final SpaceMarineConverter spaceMarineConverter;

    public void addNewMarine(SpaceMarineCreateRequestDto dto) {
        spaceMarineRepository.save(spaceMarineConverter.convertFromRequestToSpaceMarine(dto));
    }

    public SpaceMarineResponseDto getById(Long id) {
        Optional<SpaceMarine> spaceMarineOptional = spaceMarineRepository.findById(id);
        return spaceMarineConverter.convertFromSpaceMarineToResponse(
                spaceMarineOptional.orElseThrow(() -> new EntityNotFoundException("SpaceMarine с таким id не найден!")));
    }
}
