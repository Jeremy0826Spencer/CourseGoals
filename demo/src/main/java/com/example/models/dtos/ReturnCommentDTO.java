package com.example.models.dtos;

public class ReturnCommentDTO {

    private Long commentId;
    private String commentText;
    private Long goalId;

    public ReturnCommentDTO() {
    }

    public ReturnCommentDTO(Long commentId, String commentText, Long goalId) {
        this.commentId = commentId;
        this.commentText = commentText;
        this.goalId = goalId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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
