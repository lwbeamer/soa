package itmo.corp.authservice.exception.handler;

import itmo.corp.authservice.dto.ErrorDto;
import itmo.corp.authservice.exception.ExceptionCode;
import itmo.corp.authservice.exception.NoSuchUserException;
import itmo.corp.authservice.exception.PasswordIsIncorrectException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDto> handleException(RuntimeException ex) {
        log.error("Unexpected exception: ", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorDto.builder()
                        .code(ExceptionCode.UnexpectedInternalError)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler({NoSuchUserException.class, PasswordIsIncorrectException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        log.error("Access denied exception: ", ex);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ErrorDto.builder()
                        .code(ExceptionCode.AccessForbidden)
                        .message(ex.getMessage())
                        .build());
    }
}
