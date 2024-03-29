package itmo.corp.secondaryservice.service;

import itmo.corp.secondaryservice.client.SpacemarineClient;
import itmo.corp.secondaryservice.dto.Error;
import itmo.corp.secondaryservice.dto.Page;
import itmo.corp.secondaryservice.dto.SpaceMarine;
import itmo.corp.secondaryservice.dto.SpaceMarineResponseDto;
import itmo.corp.secondaryservice.dto.SpaceMarineUpdateDto;
import itmo.corp.secondaryservice.exception.ClientException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class StarshipService {

    private final SpacemarineClient client;

    public StarshipService(SpacemarineClient spacemarineClient) {
        this.client = spacemarineClient;
    }


    public SpaceMarineResponseDto loadToStarship(long starshipId, long spaceMarineId, String token) {
        SpaceMarineResponseDto spaceMarine = client.getSpaceMarineById(spaceMarineId, token);

        spaceMarine.setStarshipId(starshipId);

        SpaceMarineUpdateDto request = SpaceMarineUpdateDto.builder()
                .name(spaceMarine.getName())
                .achievements(spaceMarine.getAchievements())
                .weaponType(spaceMarine.getWeaponType())
                .coordinates(spaceMarine.getCoordinates())
                .category(spaceMarine.getCategory())
                .chapter(spaceMarine.getChapter())
                .health(spaceMarine.getHealth())
                .starshipId(starshipId)
                .build();

        Error error = client.updateSpaceMarineRequest(spaceMarineId, request, token);
        if (error != null) throw new ClientException(error);
        return spaceMarine;
    }


    public void unloadAllFromStarship(long starshipId, String token) {
        Error error;
        try {
            Page<SpaceMarine> spaceMarinePage = client.getAllSpaceMarinesInStarship(starshipId, token);
            if (spaceMarinePage.getObjects().isEmpty()) throw new ClientException(
                    Error.builder()
                            .code(418)
                            .timestamp(Instant.now())
                            .message("Корабля не существует либо он пуст!")
                            .build()

            );
            for (SpaceMarine object : spaceMarinePage.getObjects()) {
                SpaceMarineUpdateDto request = SpaceMarineUpdateDto.builder()
                        .name(object.getName())
                        .achievements(object.getAchievements())
                        .weaponType(object.getWeaponType())
                        .coordinates(object.getCoordinates())
                        .category(object.getCategory())
                        .chapter(object.getChapter())
                        .health(object.getHealth())
                        .starshipId(null)
                        .build();
                error = client.updateSpaceMarineRequest(object.getId(), request, token);
                if (error != null) throw new ClientException(error);
                Thread.sleep(100);
            }
        } catch (ClientException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new ClientException(Error.builder()
                    .code(418)
                    .timestamp(Instant.now())
                    .message("Ошибка при запросе на главный сервис!" + e.getMessage() + "\n" + e.getCause())
                    .build());
        }

    }
}
