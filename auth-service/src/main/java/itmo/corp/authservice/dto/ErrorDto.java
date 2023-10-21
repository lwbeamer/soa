package itmo.corp.authservice.dto;

import itmo.corp.authservice.exception.ExceptionCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private ExceptionCode code;
    private String message;
}
