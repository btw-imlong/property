package com.example.PropertyRent.Controller;

import com.example.PropertyRent.Dto.*;
import com.example.PropertyRent.DtoRequest.*;
import com.example.PropertyRent.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Registration successful",
                        authService.register(request)
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Login successful",
                        authService.login(request)
                )
        );
    }
}
