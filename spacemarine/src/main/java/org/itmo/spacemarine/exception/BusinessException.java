package org.itmo.spacemarine.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ExceptionCode code;

    public BusinessException(ExceptionCode code, String message) {
        super(message);
        this.code = code;
    }

}
