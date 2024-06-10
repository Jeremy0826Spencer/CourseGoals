package com.example.services;

import com.example.jwt.JwtAuthResponse;
import com.example.models.dtos.RegisterDTO;
import com.example.models.dtos.UserLoginDTO;

public interface AuthService {
    String register(RegisterDTO registerDTO);

    JwtAuthResponse login(UserLoginDTO userLoginDTO);
}
