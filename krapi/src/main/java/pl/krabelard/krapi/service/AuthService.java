package pl.krabelard.krapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import pl.krabelard.krapi.dto.LoginRequest;
import pl.krabelard.krapi.dto.LoginResponse;
import pl.krabelard.krapi.dto.RegisterRequest;
import pl.krabelard.krapi.dto.RegisterResponse;
import pl.krabelard.krapi.model.User;
import pl.krabelard.krapi.repository.UserRepository;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public RegisterResponse nativeRegister(RegisterRequest registerRequest) {
        checkForConflicts(registerRequest);

        val uuid = UUID.randomUUID();
        val username = registerRequest.username();
        val email = registerRequest.email();
        val password = registerRequest.password();

        val user = User.builder()
                .uuid(uuid)
                .username(username)
                .email(email)
                .hashedPassword(passwordEncoder.encode(password))
                .build();

        userRepository.save(user);

        return RegisterResponse.builder()
                .uuid(uuid)
                .username(username)
                .email(email)
                .build();
    }

    public LoginResponse nativeLogin(LoginRequest loginRequest) {
        return null;
    }

    private void checkForConflicts(RegisterRequest registerRequest) {
        checkIfUsernameTaken(registerRequest.username());
        checkIfEmailTaken(registerRequest.email());
    }

    private void checkIfUsernameTaken(String username) {
        // TODO - throw some custom exception
        if (userRepository.existsByUsername(username)) {
            log.error("{} - username taken", username);
        }
    }

    private void checkIfEmailTaken(String email) {
        // TODO - throw some custom exception
        if (userRepository.existsByEmail(email)) {
            log.error("{} - email taken", email);
        }
    }

}
