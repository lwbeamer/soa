package org.itmo.spacemarine.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.itmo.spacemarine.entity.SpaceMarine;
import org.itmo.spacemarine.model.Error;
import org.itmo.spacemarine.model.SpaceMarineCreateRequest;
import org.itmo.spacemarine.service.SpaceMarinesService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.OffsetDateTime;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class SpaceMarinesApiController {

    private final SpaceMarinesService spaceMarinesService;

    public SpaceMarinesApiController(SpaceMarinesService spaceMarinesService) {
        this.spaceMarinesService = spaceMarinesService;
    }


    /**
     * GET /space-marines : Получить всех десантников
     *
     * @param sort Имя поля и порядок сортировки (если не указан - ASC) (optional)
     * @param page Номер страницы (optional)
     * @param pageSize Размер страницы (optional)
     * @param name Фильтр по имени (optional)
     * @param health Фильтр по здоровью (optional)
     * @param achievements Фильтр по достижениям (optional)
     * @param category Фильтр по категории (optional)
     * @param weaponType Фильтр по типу оружия (optional)
     * @param chapterName Фильтр по имени главы (optional)
     * @param chapterWorld Фильтр по миру главы (optional)
     * @param creationDate Фильтр по дате создания (optional)
     * @param coordinatesX Фильтр по координате X (optional)
     * @param coordinatesY Фильтр по координате Y (optional)
     * @return Успех (status code 200)
     *         or Возвращает тело для ошибки (код ошибки, время и сообщение) (status code 200)
     */
    @Operation(
            operationId = "spaceMarinesGet",
            summary = "Получить всех десантников",
            tags = { "Space Marines" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успех", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SpaceMarine.class))
                    }),
                    @ApiResponse(responseCode = "200", description = "Возвращает тело для ошибки (код ошибки, время и сообщение)", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/space-marines",
            produces = { "application/json" }
    )
    public ResponseEntity<List<SpaceMarine>> spaceMarinesGet(
            @Parameter(name = "sort", description = "Имя поля и порядок сортировки (если не указан - ASC)") @Valid @RequestParam(value = "sort", required = false) List<String> sort,
            @Min(1) @Parameter(name = "page", description = "Номер страницы") @Valid @RequestParam(value = "page", required = false) Integer page,
            @Min(1) @Parameter(name = "pageSize", description = "Размер страницы") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @Parameter(name = "name", description = "Фильтр по имени") @Valid @RequestParam(value = "name", required = false) List<String> name,
            @Parameter(name = "health", description = "Фильтр по здоровью") @Valid @RequestParam(value = "health", required = false) List<Integer> health,
            @Parameter(name = "achievements", description = "Фильтр по достижениям") @Valid @RequestParam(value = "achievements", required = false) List<String> achievements,
            @Parameter(name = "category", description = "Фильтр по категории") @Valid @RequestParam(value = "category", required = false) List<String> category,
            @Parameter(name = "weaponType", description = "Фильтр по типу оружия") @Valid @RequestParam(value = "weaponType", required = false) List<String> weaponType,
            @Parameter(name = "chapterName", description = "Фильтр по имени главы") @Valid @RequestParam(value = "chapterName", required = false) List<String> chapterName,
            @Parameter(name = "chapterWorld", description = "Фильтр по миру главы") @Valid @RequestParam(value = "chapterWorld", required = false) List<String> chapterWorld,
            @Parameter(name = "creationDate", description = "Фильтр по дате создания") @Valid @RequestParam(value = "creationDate", required = false) List<OffsetDateTime> creationDate,
            @Parameter(name = "coordinatesX", description = "Фильтр по координате X") @Valid @RequestParam(value = "coordinatesX", required = false) List<Double> coordinatesX,
            @Parameter(name = "coordinatesY", description = "Фильтр по координате Y") @Valid @RequestParam(value = "coordinatesY", required = false) List<Long> coordinatesY
    ) {
        return spaceMarinesService.spaceMarinesGet(sort, page, pageSize, name, health, achievements, category, weaponType, chapterName, chapterWorld, creationDate, coordinatesX, coordinatesY);
    }


    /**
     * DELETE /space-marines/{id} : Удалить десантника
     *
     * @param id ID десантника (required)
     * @return Успех (status code 204)
     *         or Возвращает тело для ошибки (код ошибки, время и сообщение) (status code 200)
     */

    @Operation(
            operationId = "spaceMarinesIdDelete",
            summary = "Удалить десантника",
            tags = { "Space Marines" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Успех"),
                    @ApiResponse(responseCode = "200", description = "Возвращает тело для ошибки (код ошибки, время и сообщение)", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/space-marines/{id}",
            produces = { "application/json" }
    )
    public ResponseEntity<Void> spaceMarinesIdDelete(
            @Min(1) @Parameter(name = "id", description = "ID десантника", required = true) @PathVariable("id") Long id
    ) {
        return spaceMarinesService.spaceMarinesIdDelete(id);
    }


    /**
     * GET /space-marines/{id} : Получить десантника по ID
     *
     * @param id ID десантника (required)
     * @return Успех (status code 200)
     *         or Возвращает тело для ошибки (код ошибки, время и сообщение) (status code 200)
     */
    @Operation(
            operationId = "spaceMarinesIdGet",
            summary = "Получить десантника по ID",
            tags = { "Space Marines" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успех", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = SpaceMarine.class))
                    }),
                    @ApiResponse(responseCode = "200", description = "Возвращает тело для ошибки (код ошибки, время и сообщение)", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/space-marines/{id}",
            produces = { "application/json" }
    )
    public ResponseEntity<SpaceMarine> spaceMarinesIdGet(
            @Min(1) @Parameter(name = "id", description = "ID десантника", required = true) @PathVariable("id") Long id
    ) {
        return spaceMarinesService.spaceMarinesIdGet(id);
    }


    /**
     * PUT /space-marines/{id} : Обновить десантника
     *
     * @param id ID десантника (required)
     * @param spaceMarineCreateRequest  (required)
     * @return Успех (status code 204)
     *         or Возвращает тело для ошибки (код ошибки, время и сообщение) (status code 200)
     */
    @Operation(
            operationId = "spaceMarinesIdPut",
            summary = "Обновить десантника",
            tags = { "Space Marines" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Успех"),
                    @ApiResponse(responseCode = "200", description = "Возвращает тело для ошибки (код ошибки, время и сообщение)", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.PUT,
            value = "/space-marines/{id}",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> spaceMarinesIdPut(
            @Min(1) @Parameter(name = "id", description = "ID десантника", required = true) @PathVariable("id") Long id,
            @Parameter(name = "SpaceMarineCreateRequest", description = "", required = true) @Valid @RequestBody SpaceMarineCreateRequest spaceMarineCreateRequest
    ) {
        return spaceMarinesService.spaceMarinesIdPut(id, spaceMarineCreateRequest);
    }


    /**
     * POST /space-marines : Добавить нового десантника
     *
     * @param spaceMarineCreateRequest  (required)
     * @return Десантник успешно создан (status code 204)
     *         or Возвращает тело для ошибки (код ошибки, время и сообщение) (status code 200)
     */
    @Operation(
            operationId = "spaceMarinesPost",
            summary = "Добавить нового десантника",
            tags = { "Space Marines" },
            responses = {
                    @ApiResponse(responseCode = "204", description = "Десантник успешно создан"),
                    @ApiResponse(responseCode = "200", description = "Возвращает тело для ошибки (код ошибки, время и сообщение)", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/space-marines",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    public ResponseEntity<Void> spaceMarinesPost(
            @Parameter(name = "SpaceMarineCreateRequest", description = "", required = true) @Valid @RequestBody SpaceMarineCreateRequest spaceMarineCreateRequest
    ) {
        return spaceMarinesService.spaceMarinesPost(spaceMarineCreateRequest);
    }

}
