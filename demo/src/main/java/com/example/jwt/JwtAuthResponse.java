package com.example.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String role;

    public JwtAuthResponse() {
    }
    public JwtAuthResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
    public String getTokenType() {
        return tokenType;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

}
