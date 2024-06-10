package com.example.controllers;

import com.example.jwt.JwtAuthResponse;
import com.example.models.dtos.RegisterDTO;
import com.example.models.dtos.UserLoginDTO;
import com.example.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/API/V1/users")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        String response = authService.register(registerDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody UserLoginDTO loginDTO){
        JwtAuthResponse jwtAuthResponse = authService.login(loginDTO);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

}
