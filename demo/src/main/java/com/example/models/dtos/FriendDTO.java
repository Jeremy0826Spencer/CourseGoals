package com.example.models.dtos;

public class FriendDTO {
    private Long userId;
    private String username;
    private int numberOfPublicGoals;

    public FriendDTO() {
    }

    public FriendDTO(Long userId, String username, int numberOfPublicGoals) {
        this.userId = userId;
        this.username = username;
        this.numberOfPublicGoals = numberOfPublicGoals;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNumberOfPublicGoals() {
        return numberOfPublicGoals;
    }

    public void setNumberOfPublicGoals(int numberOfPublicGoals) {
        this.numberOfPublicGoals = numberOfPublicGoals;
    }
}
