package pl.krabelard.krapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.krabelard.krapi.dto.LoginRequest;
import pl.krabelard.krapi.dto.LoginResponse;
import pl.krabelard.krapi.dto.RegisterRequest;
import pl.krabelard.krapi.dto.RegisterResponse;
import pl.krabelard.krapi.service.AuthService;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<RegisterResponse> nativeRegister(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> nativeLogin(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(null);
    }

}
