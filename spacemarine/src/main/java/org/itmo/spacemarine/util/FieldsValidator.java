package org.itmo.spacemarine.util;

import org.itmo.spacemarine.dto.SpaceMarineCreateRequestDto;
import org.itmo.spacemarine.dto.SpaceMarineUpdateDto;
import org.itmo.spacemarine.exception.BusinessException;
import org.itmo.spacemarine.exception.ExceptionCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FieldsValidator {


    @Value("${space-marine.constraints.health-min-value:0}")
    private Integer healthMinValue;

    @Value("${space-marine.constraints.x-max-value:872}")
    private Integer xMaxValue;

    @Value("${space-marine.constraints.y-max-value:110}")
    private Integer yMaxValue;

    public void validateCreateRequest(SpaceMarineCreateRequestDto requestDto) {
        checkHealth(requestDto.getHealth());
        checkCoordinateX(requestDto.getCoordinates().getX());
        checkCoordinateY(requestDto.getCoordinates().getY());
    }

    public void validateUpdateRequest(SpaceMarineUpdateDto requestDto) {
        checkHealth(requestDto.getHealth());
        checkCoordinateX(requestDto.getCoordinates().getX());
        checkCoordinateY(requestDto.getCoordinates().getY());
    }


    public void checkHealth(Long health) {
        if (health < healthMinValue)
            throw new BusinessException(ExceptionCode.RequestValidationFailed, "Минимальное значение health = " + healthMinValue);
    }

    public void checkCoordinateX(Long x) {
        if (x > xMaxValue)
            throw new BusinessException(ExceptionCode.RequestValidationFailed, "Максимальное значение x = " + xMaxValue);
    }

    public void checkCoordinateY(Long y) {
        if (y < yMaxValue)
            throw new BusinessException(ExceptionCode.RequestValidationFailed, "Максимальное значение y = " + yMaxValue);
    }
}
