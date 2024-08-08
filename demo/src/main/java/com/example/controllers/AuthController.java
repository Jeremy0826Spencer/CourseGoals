package com.example.controllers;

import com.example.jwt.JwtAuthResponse;
import com.example.models.dtos.RegisterDTO;
import com.example.models.dtos.UserLoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

//Interface for the AuthControllerImpl
//This class that implements this is used for logging in and registering
public interface AuthController {
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO);
    public ResponseEntity<JwtAuthResponse> login(@RequestBody UserLoginDTO loginDTO);
}
