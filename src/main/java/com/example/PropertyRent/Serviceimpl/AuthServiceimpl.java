package com.example.PropertyRent.Serviceimpl;

import com.example.PropertyRent.Dto.LoginResponse;
import com.example.PropertyRent.Dto.RegisterResponse;
import com.example.PropertyRent.DtoRequest.LoginRequest;
import com.example.PropertyRent.DtoRequest.RegisterRequest;
import com.example.PropertyRent.Entity.RoleType;
import com.example.PropertyRent.Entity.User;
import com.example.PropertyRent.Repositories.UserRepository;
import com.example.PropertyRent.Security.JwtUtil;
import com.example.PropertyRent.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceimpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        if (userRepository.existsByFullName(request.getFullName())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Full name already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // ✅ NORMAL REGISTER = USER
        user.setRole(RoleType.USER);

        userRepository.save(user);

        return new RegisterResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole().name()
        );
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password")
                );

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new LoginResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().name(),
                token
        );
    }
}
