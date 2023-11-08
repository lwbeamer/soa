package org.itmo.spacemarine.converter;

import org.itmo.spacemarine.dto.SpaceMarineCreateRequestDto;
import org.itmo.spacemarine.dto.SpaceMarineResponseDto;
import org.itmo.spacemarine.entity.SpaceMarine;
import org.itmo.spacemarine.entity.Weapon;
import org.springframework.stereotype.Service;

@Service
public class SpaceMarineConverter {

    public SpaceMarine convertFromRequestToSpaceMarine(SpaceMarineCreateRequestDto dto) {
        return SpaceMarine.builder()
                .achievements(dto.getAchievements())
                .chapter(dto.getChapter())
                .coordinates(dto.getCoordinates())
                .category(dto.getCategory())
                .name(dto.getName())
                .health(dto.getHealth())
                .weaponType(dto.getWeaponType())
                .build();
    }

    public SpaceMarineResponseDto convertFromSpaceMarineToResponse(SpaceMarine spaceMarine) {
        return SpaceMarineResponseDto.builder()
                .achievements(spaceMarine.getAchievements())
                .creationDate(spaceMarine.getCreationDate())
                .id(spaceMarine.getId())
                .category(spaceMarine.getCategory())
                .chapter(spaceMarine.getChapter())
                .coordinates(spaceMarine.getCoordinates())
                .health(spaceMarine.getHealth())
                .name(spaceMarine.getName())
                .weaponType(spaceMarine.getWeaponType())
                .build();
    }

}
