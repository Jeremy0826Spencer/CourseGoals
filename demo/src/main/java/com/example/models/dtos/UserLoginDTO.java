package com.example.models.dtos;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDTO {

    @NotBlank
    private String usernameOrEmail;
    @NotBlank
    private String password;

    public UserLoginDTO() {

    }

    public UserLoginDTO(String username, String password) {
        this.usernameOrEmail = username;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "usernameOrEmail='" + usernameOrEmail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
