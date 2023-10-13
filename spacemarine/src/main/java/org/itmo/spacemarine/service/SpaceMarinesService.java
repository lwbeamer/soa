package org.itmo.spacemarine.service;

import org.itmo.spacemarine.entity.SpaceMarine;
import org.itmo.spacemarine.model.SpaceMarineCreateRequest;
import org.itmo.spacemarine.repository.SpaceMarineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceMarinesService {
    private final SpaceMarineRepository spaceMarineRepository;

    public SpaceMarinesService(SpaceMarineRepository spaceMarineRepository) {
        this.spaceMarineRepository = spaceMarineRepository;
    }

    public ResponseEntity<Void> spaceMarinesIdDelete(Long id) {
        if (spaceMarineRepository.existsById(id)) {
            spaceMarineRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<SpaceMarine> spaceMarinesIdGet(Long id) {
        Optional<SpaceMarine> spaceMarine = spaceMarineRepository.findById(id);
        return spaceMarine.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> spaceMarinesPost(SpaceMarineCreateRequest spaceMarineCreateRequest) {
        SpaceMarine spaceMarine =  new SpaceMarine();
        spaceMarine.setAchievements(spaceMarineCreateRequest.getAchievements());
        spaceMarine.setName(spaceMarineCreateRequest.getName());
        spaceMarine.setChapterName(spaceMarineCreateRequest.getChapter().getName());
        spaceMarine.setChapterWorld(spaceMarineCreateRequest.getChapter().getWorld());
        spaceMarine.setX(spaceMarineCreateRequest.getCoordinates().getX());
        spaceMarine.setY(spaceMarineCreateRequest.getCoordinates().getY());
        spaceMarine.setCategory(spaceMarineCreateRequest.getCategory());
        spaceMarine.setWeaponType(spaceMarineCreateRequest.getWeaponType());
        spaceMarine = spaceMarineRepository.save(spaceMarine);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> spaceMarinesIdPut(Long id, SpaceMarineCreateRequest spaceMarineCreateRequest) {
        return null;
    }

    public ResponseEntity<List<SpaceMarine>> spaceMarinesGet(List<String> sort, Integer page, Integer pageSize, List<String> name, List<Integer> health, List<String> achievements, List<String> category, List<String> weaponType, List<String> chapterName, List<String> chapterWorld, List<OffsetDateTime> creationDate, List<Double> coordinatesX, List<Long> coordinatesY) {
        return null;
    }
}
