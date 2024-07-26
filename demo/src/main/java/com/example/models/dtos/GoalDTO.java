package com.example.models.dtos;

import com.example.models.CourseGoal;
import com.example.models.enums.PrivacyEnum;
import jakarta.validation.constraints.NotBlank;

public class GoalDTO {

    private Long goalId;
    @NotBlank(message = "Must include title")
    private String title;
    @NotBlank(message = "Must include a description.")
    private String body;
    private PrivacyEnum privacyEnum;
    public GoalDTO() {
    }

    public GoalDTO(Long goalId, String title, String body, PrivacyEnum privacyEnum) {
        this.goalId = goalId;
        this.title = title;
        this.body = body;
        this.privacyEnum = privacyEnum;
    }
    public Long getGoalId() {
        return goalId;
    }
    public void setGoalId(Long goalId) {
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

    public PrivacyEnum getPrivacyEnum() {
        return privacyEnum;
    }

    public void setPrivacyEnum(PrivacyEnum privacyEnum) {
        this.privacyEnum = privacyEnum;
    }

    @Override
    public String toString() {
        return "GoalDTO{" +
                "goalId=" + goalId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", privacyEnum=" + privacyEnum +
                '}';
    }

    public static GoalDTO convertToDTO(CourseGoal goal){
        return new GoalDTO(goal.getGoalId(), goal.getTitle(), goal.getBody(), goal.getPrivacy());
    }
}
