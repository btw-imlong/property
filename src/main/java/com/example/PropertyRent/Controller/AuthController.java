package com.example.PropertyRent.Controller;

import com.example.PropertyRent.Dto.*;
import com.example.PropertyRent.DtoRequest.*;
import com.example.PropertyRent.DtoRequest.BecomeAgent;
import com.example.PropertyRent.Entity.User;
import com.example.PropertyRent.Service.AuthService;
import com.example.PropertyRent.Service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

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
   
    @PutMapping("/becomeagent")
    public ResponseEntity<String> becomeAgent(@RequestBody BecomeAgent request) {
        try {
            User updatedUser = userService.becomeAgent(request);
            return ResponseEntity.ok("User " + updatedUser.getFullName() + " is now an AGENT");
        } catch (Exception e) {
            e.printStackTrace();  // <--- prints the real error in console
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

}
