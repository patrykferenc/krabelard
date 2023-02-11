package pl.krabelard.krapi.service;

import org.springframework.stereotype.Service;
import pl.krabelard.krapi.dto.LoginRequest;
import pl.krabelard.krapi.dto.LoginResponse;
import pl.krabelard.krapi.dto.RegisterRequest;
import pl.krabelard.krapi.dto.RegisterResponse;

@Service
public class AuthService {

    public RegisterResponse nativeRegister(RegisterRequest registerRequest) {
        return null;
    }

    public LoginResponse nativeLogin(LoginRequest loginRequest) {
        return null;
    }

}
