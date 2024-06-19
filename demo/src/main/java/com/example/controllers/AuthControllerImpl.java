package com.example.controllers;

import com.example.jwt.JwtAuthResponse;
import com.example.models.dtos.RegisterDTO;
import com.example.models.dtos.UserLoginDTO;
import com.example.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API/V1/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthControllerImpl implements AuthController{
    private AuthService authService;

    @Autowired
    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO){
        return authService.register(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody UserLoginDTO loginDTO){
        JwtAuthResponse jwtAuthResponse = authService.login(loginDTO);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

}
