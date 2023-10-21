package itmo.corp.authservice.dto;

import lombok.Data;

@Data
public class RegistrationRequestDto {
    private String username;
    private String password;

}
