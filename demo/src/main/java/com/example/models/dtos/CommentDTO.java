package com.example.models.dtos;

import com.example.models.CourseGoal;
import jakarta.persistence.*;

public class CommentDTO {
    private String commentText;
    private Long goalId;

    public CommentDTO() {
    }

    public CommentDTO(String commentText, Long goalId) {
        this.commentText = commentText;
        this.goalId = goalId;
    }

    public Long getGoalId() {
        return goalId;
    }

    public void setGoalId(Long goalId) {
        this.goalId = goalId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
