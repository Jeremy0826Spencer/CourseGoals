package com.example.services;

import com.example.jwt.JwtAuthResponse;
import com.example.models.dtos.RegisterDTO;
import com.example.models.dtos.UserLoginDTO;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> register(RegisterDTO registerDTO);

    JwtAuthResponse login(UserLoginDTO userLoginDTO);
}
