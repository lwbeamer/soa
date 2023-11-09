package org.itmo.spacemarine.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    // 4xx
    InvalidRequest(HttpStatus.BAD_REQUEST),

    // 404
    SpaceMarineNotFound(HttpStatus.NOT_FOUND),
    StarshipNotFound(HttpStatus.NOT_FOUND);

    private final HttpStatus status;
}
