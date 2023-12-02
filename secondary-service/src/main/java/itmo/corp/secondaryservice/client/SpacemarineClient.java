package itmo.corp.secondaryservice.client;

import itmo.corp.secondaryservice.dto.*;
import itmo.corp.secondaryservice.dto.Error;
import itmo.corp.secondaryservice.exception.ClientException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;


@Service
public class SpacemarineClient {


    @Value("${spacemarine.api}")
    private String SPACE_MARINE_BASE_URL;

    private final RestTemplate client;

    public SpacemarineClient(RestTemplate client) {
        this.client = client;
    }

    @SneakyThrows
    public SpaceMarineResponseDto getSpaceMarineById(long id, String token) {
        SpaceMarineResponseDto responseData = null;

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> jwtEntity = new HttpEntity<>(null, headers);
        headers.set("Authorization", token);

        try {
            ResponseEntity<SpaceMarineResponseDto> response = client.exchange(SPACE_MARINE_BASE_URL + "/" + id, HttpMethod.GET, jwtEntity, SpaceMarineResponseDto.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                responseData = response.getBody();
            }
        } catch (Exception e) {
            throw new ClientException(Error.builder()
                    .code(418)
                    .message("Ошибка отправки запроса на главный сервис!")
                    .timestamp(Instant.now())
                    .build());
        }

        return responseData;
    }

    @SneakyThrows
    public Error updateSpaceMarineRequest(long id, SpaceMarineUpdateDto requestData, String token) {
        Error error = null;

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<SpaceMarineUpdateDto> jwtEntity = new HttpEntity<>(requestData, headers);
        headers.set("Authorization", token);

        try {
            ResponseEntity<Error> response = client.exchange(SPACE_MARINE_BASE_URL + "/" + id, HttpMethod.PUT, jwtEntity, Error.class);
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                System.out.println("PUT request was successful");
            } else {
                error = response.getBody();
            }
        } catch (Exception e) {
            error = Error.builder()
                    .code(418)
                    .message("Ошибка отправки запроса на главный сервис!")
                    .timestamp(Instant.now())
                    .build();
        }
        return error;
    }

    @SneakyThrows
    public Page<SpaceMarine> getAllSpaceMarinesInStarship(long starshipId, String token) {

        Page<SpaceMarine> responseData = null;

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(SPACE_MARINE_BASE_URL)
                .queryParam("starshipId", String.valueOf(starshipId))
                .queryParam("pageSize", String.valueOf(Integer.MAX_VALUE))
                .encode()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<SpaceMarineUpdateDto> jwtEntity = new HttpEntity<>(null, headers);
        headers.set("Authorization", token);

        try {
            ResponseEntity<Page<SpaceMarine>> response = client.exchange(
                    urlTemplate,
                    HttpMethod.GET,
                    jwtEntity,
                    new ParameterizedTypeReference<Page<SpaceMarine>>() {
                    }
            );

            if (response.getStatusCode().equals(HttpStatus.OK)) {
                responseData = response.getBody();
            }


        } catch (Exception e) {
            throw new ClientException(Error.builder()
                    .code(418)
                    .message("Ошибка отправки запроса на главный сервис!")
                    .timestamp(Instant.now())
                    .build());
        }
        return responseData;
    }
}
