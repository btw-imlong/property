package com.example.PropertyRent.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.PropertyRent.Dto.RegisterResponse;
import com.example.PropertyRent.DtoRequest.RegisterRequest;
import com.example.PropertyRent.Entity.User;
import com.example.PropertyRent.Repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByFullName(request.getFullName())) {
            throw new RuntimeException("Full name already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new RegisterResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone()
        );
    }

   }
