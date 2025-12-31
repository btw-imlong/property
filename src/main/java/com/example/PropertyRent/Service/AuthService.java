package com.example.PropertyRent.Service;

import com.example.PropertyRent.Dto.LoginResponse;
import com.example.PropertyRent.Dto.RegisterResponse;
import com.example.PropertyRent.DtoRequest.LoginRequest;
import com.example.PropertyRent.DtoRequest.RegisterRequest;

public interface AuthService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
