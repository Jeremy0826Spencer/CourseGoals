package com.example.models.dtos;

import jakarta.validation.constraints.NotBlank;

public class FriendGoalDTO {

    private int goalId;
    @NotBlank(message = "Must include title")
    private String title;
    @NotBlank(message = "Must include a description.")
    private String body;

    public FriendGoalDTO() {
    }

    public FriendGoalDTO(int goalId, String title, String body) {
        this.goalId = goalId;
        this.title = title;
        this.body = body;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "FriendGoalDTO{" +
                "goalId=" + goalId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
