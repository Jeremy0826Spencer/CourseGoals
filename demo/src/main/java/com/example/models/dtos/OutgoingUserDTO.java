package com.example.models.dtos;

import com.example.models.User;

public class OutgoingUserDTO {

    private Long userId;
    private String username;
    private boolean isAccountLocked;

    public OutgoingUserDTO() {
    }

    public OutgoingUserDTO(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }
    public OutgoingUserDTO(Long userId, String username, boolean isAccountLocked) {
        this.userId = userId;
        this.username = username;
        this.isAccountLocked = isAccountLocked;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isAccountLocked() {
        return isAccountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        isAccountLocked = accountLocked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static OutgoingUserDTO mapToOutUserDTO(User user){
        return new OutgoingUserDTO(user.getId(), user.getUsername(), user.isAccountLocked());
    }

    @Override
    public String toString() {
        return "OutgoingUserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
