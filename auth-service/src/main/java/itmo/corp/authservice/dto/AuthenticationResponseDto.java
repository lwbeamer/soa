package itmo.corp.authservice.dto;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponseDto {
    String username;
    String token;
}
