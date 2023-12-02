package itmo.corp.secondaryservice.controller;

import itmo.corp.secondaryservice.dto.Error;
import itmo.corp.secondaryservice.dto.SpaceMarineResponseDto;
import itmo.corp.secondaryservice.exception.ClientException;
import itmo.corp.secondaryservice.service.StarshipService;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/starships")
public class StarshipController {

    private final StarshipService starshipService;

    public StarshipController(StarshipService starshipService) {
        this.starshipService = starshipService;
    }

    @PutMapping("/{starship_id}/load/{space-marine_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public ResponseEntity<Object> loadSpaceMarine(
            @RequestHeader("Authorization") String token,
            @PathVariable("starship_id") String starshipId,
            @PathVariable("space-marine_id") String spaceMarineId
    ) {
        long starshipIdLong;
        long spaceMarineIdLong;
        try {
            starshipIdLong = Long.parseLong(starshipId);
            spaceMarineIdLong = Long.parseLong(spaceMarineId);
        } catch (NumberFormatException ex) {
            return ResponseEntity
                    .status(400)
                    .body(Error.builder()
                            .code(400)
                            .message("Некорректные параметры запроса")
                            .timestamp(Instant.now()).build());
        }

        try {
            SpaceMarineResponseDto responseDto = starshipService.loadToStarship(starshipIdLong, spaceMarineIdLong, token);
            return ResponseEntity.ok(responseDto);
        } catch (ClientException e) {
            return ResponseEntity
                    .status(e.getError().getCode())
                    .body(e.getError());
        }
    }

    @PutMapping("/{starship_id}/unload-all")
    @Produces(MediaType.TEXT_PLAIN)
    public ResponseEntity<Object> unloadAllSpaceMarines(
            @RequestHeader("Authorization") String token,
            @PathVariable("starship_id") String starshipId
    ) {
        long starshipIdLong;
        try {
            starshipIdLong = Long.parseLong(starshipId);
        } catch (NumberFormatException ex) {
            return ResponseEntity
                    .status(400)
                    .body(Error.builder()
                            .code(400)
                            .message("Некорректные параметры запроса")
                            .timestamp(Instant.now()).build());
        }

        try {
            starshipService.unloadAllFromStarship(starshipIdLong, token);
            return ResponseEntity.noContent().build();
        } catch (ClientException e) {
            return ResponseEntity
                    .status(e.getError().getCode())
                    .body(e.getError());
        }
    }
}