package org.itmo.spacemarine.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.itmo.spacemarine.model.Error;
import org.itmo.spacemarine.model.InlineResponse200;
import org.itmo.spacemarine.model.SpaceMarine;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/util")
@Tag(name = "space-utils", description = "Утилитарные операции с десантниками")
public class SpaceUtilsApiController {

    /**
     * GET /space-utils/{field-name}/max : Применение агрегатной функции MAX к выбранному полю
     *
     * @param fieldName Название поля (required)
     * @return Успех (status code 200)
     *         or Возвращает тело для ошибки (код ошибки, время и сообщение) (status code 200)
     */
    @Operation(
            operationId = "spaceUtilsFieldNameMaxGet",
            summary = "Применение агрегатной функции MAX к выбранному полю",
            tags = { "Space Utils" },
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
            value = "/space-utils/{field-name}/max",
            produces = { "application/json" }
    )
    public ResponseEntity<SpaceMarine> spaceUtilsFieldNameMaxGet(
            @Parameter(name = "field-name", description = "Название поля", required = true) @PathVariable("field-name") String fieldName
    ) {
        return spaceUtilsFieldNameMaxGet(fieldName);
    }


    /**
     * GET /space-utils/{field-name}/min : Применение агрегатной функции MIN к выбранному полю
     *
     * @param fieldName Название поля (required)
     * @return Успех (status code 200)
     *         or Возвращает тело для ошибки (код ошибки, время и сообщение) (status code 200)
     */
    @Operation(
            operationId = "spaceUtilsFieldNameMinGet",
            summary = "Применение агрегатной функции MIN к выбранному полю",
            tags = { "Space Utils" },
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
            value = "/space-utils/{field-name}/min",
            produces = { "application/json" }
    )
    public ResponseEntity<SpaceMarine> spaceUtilsFieldNameMinGet(
            @Parameter(name = "field-name", description = "Название поля", required = true) @PathVariable("field-name") String fieldName
    ) {
        return spaceUtilsFieldNameMinGet(fieldName);
    }


    /**
     * GET /space-utils/{field-name}/sum : Применение агрегатной функции SUM к выбранному полю
     *
     * @param fieldName Название поля (required)
     * @return Успех (status code 200)
     *         or Возвращает тело для ошибки (код ошибки, время и сообщение) (status code 200)
     */
    @Operation(
            operationId = "spaceUtilsFieldNameSumGet",
            summary = "Применение агрегатной функции SUM к выбранному полю",
            tags = { "Space Utils" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успех", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = InlineResponse200.class))
                    }),
                    @ApiResponse(responseCode = "200", description = "Возвращает тело для ошибки (код ошибки, время и сообщение)", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/space-utils/{field-name}/sum",
            produces = { "application/json" }
    )
    public ResponseEntity<?> spaceUtilsFieldNameSumGet(
            @Parameter(name = "field-name", description = "Название поля", required = true) @PathVariable("field-name") String fieldName
    ) {
        return spaceUtilsFieldNameSumGet(fieldName);
    }
}
