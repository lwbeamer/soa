package org.itmo.spacemarine.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.itmo.spacemarine.dto.Error;
import org.itmo.spacemarine.exception.AccessForbiddenException;
import org.itmo.spacemarine.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@Slf4j
@ControllerAdvice
public class GlobalHttpExceptionHandler {



    @ExceptionHandler({NoHandlerFoundException.class})
    public ResponseEntity<Error> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpServletRequest httpServletRequest) {
        Error apiErrorResponse = Error.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("Неверный uri")
                .timestamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(apiErrorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Error> handleBusinessException(BusinessException ex) {
        String message = ex.getMessage();
        log.info(message, ex);
        return ResponseEntity
                .status(ex.getCode().getStatus())
                .body(Error.builder()
                        .code(ex.getCode().getStatus().value())
                        .message(message)
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(AccessForbiddenException.class)
    public ResponseEntity<Error> handleAccessForbiddenException(AccessForbiddenException ex) {
        String message = ex.getMessage();
        log.info(message, ex);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Error.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .message(message)
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<Error> handlePropertyValueException(PropertyValueException ex) {
        String message = ex.getMessage();
        log.info(message, ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Неверные данные в теле запроса: " + message)
                        .timestamp(Instant.now())
                        .build());
    }
}
