package com.example.models;

import com.example.models.dtos.GoalDTO;
import com.example.models.enums.PrivacyEnum;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course_goals")
public class CourseGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long goalId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private PrivacyEnum privacy;
    @OneToMany(mappedBy = "goal", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notes> notes = new ArrayList<>();
    @OneToMany(mappedBy = "goal", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    public CourseGoal() {
    }

    public CourseGoal(String title, String body, User user, PrivacyEnum privacy) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.privacy = privacy;
    }

    public Long getGoalId() {
        return goalId;
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

    public List<Notes> getNotes() {
        return notes;
    }

    public void setNotes(List<Notes> notes) {
        this.notes = notes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PrivacyEnum getPrivacy() {
        return privacy;
    }

    public void setPrivacy(PrivacyEnum privacy) {
        this.privacy = privacy;
    }

    @Override
    public String toString() {
        return "CourseGoal{" +
                "goalId=" + goalId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", notes=" + notes +
                ", user=" + user +
                ", privacy=" + privacy +
                '}';
    }

}
