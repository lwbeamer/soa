package itmo.corp.authservice.service;

import itmo.corp.authservice.dto.AuthenticationRequestDto;
import itmo.corp.authservice.dto.RegistrationRequestDto;
import itmo.corp.authservice.enity.User;
import itmo.corp.authservice.exception.NoSuchUserException;
import itmo.corp.authservice.exception.PasswordIsIncorrectException;
import itmo.corp.authservice.exception.UserAlreadyExistException;
import itmo.corp.authservice.repository.UserRepository;
import itmo.corp.authservice.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;


    public String authenticate(AuthenticationRequestDto request) throws InvalidKeySpecException, NoSuchAlgorithmException {
        Optional<User> userOpt = userRepository.findById(request.getUsername());
        if (userOpt.isEmpty()) throw new NoSuchUserException("Пользователь с таким логином не зарегистрирован!");
        if (!passwordEncoder.matches(request.getPassword(),userOpt.get().getPassword())) throw new PasswordIsIncorrectException("Неверный пароль!");
        return jwtTokenProvider.generateJwtToken(request.getUsername());
    }

    public User register(RegistrationRequestDto request) {
        Optional<User> userOpt = userRepository.findById(request.getUsername());

        if (userOpt.isPresent()) throw new UserAlreadyExistException("Такая учетная запись уже зарегистрирована");

        User user =  User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return userRepository.save(user);
    }

}
