package itmo.corp.authservice.controller;

import itmo.corp.authservice.dto.AuthenticationRequestDto;
import itmo.corp.authservice.dto.AuthenticationResponseDto;
import itmo.corp.authservice.dto.RegistrationRequestDto;
import itmo.corp.authservice.enity.User;
import itmo.corp.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth/")
public class AuthController {

    private final AuthService authService;


    @PostMapping("login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        AuthenticationResponseDto response = AuthenticationResponseDto.builder()
                .username(request.getUsername())
                .token(authService.authenticate(request))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody RegistrationRequestDto requestDto) {
        User user = authService.register(requestDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
