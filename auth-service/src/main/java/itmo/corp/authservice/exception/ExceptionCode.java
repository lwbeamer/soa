package itmo.corp.authservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    // 404
    UserNotFound(HttpStatus.NOT_FOUND),

    // 403
    AccessForbidden(HttpStatus.FORBIDDEN),

    // 400
    BadRequest(HttpStatus.FORBIDDEN),

    // 5xx,
    UnexpectedInternalError(HttpStatus.INTERNAL_SERVER_ERROR);

    private final HttpStatus status;
}