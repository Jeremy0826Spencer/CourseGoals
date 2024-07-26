package com.example.models;

import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;
    private String commentText;
    @ManyToOne(fetch = FetchType.EAGER)
    private CourseGoal goal;

    public Comment() {
    }

    public Comment(String commentText, CourseGoal goal) {
        this.commentText = commentText;
        this.goal = goal;
    }

    public CourseGoal getGoal() {
        return goal;
    }

    public void setGoal(CourseGoal goal) {
        this.goal = goal;
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
