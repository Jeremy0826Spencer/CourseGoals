package com.example.models;

import com.example.models.enums.PrivacyEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int noteId;
    private String body;
    @Column(unique = true)
    private String noteImage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goalId", nullable = false)
    private CourseGoal goal;
    @Column(nullable = false)
    private PrivacyEnum privacy;

    public Notes() {
    }

    public Notes(String body, String noteImage) {
        this.body = body;
        this.noteImage = noteImage;
    }

    public int getNoteId() {
        return noteId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getNoteImage() {
        return noteImage;
    }

    public void setNoteImage(String noteImage) {
        this.noteImage = noteImage;
    }

    public CourseGoal getGoal() {
        return goal;
    }

    public void setGoal(CourseGoal goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "noteId=" + noteId +
                ", body='" + body + '\'' +
                ", noteImage='" + noteImage + '\'' +
                ", goal=" + goal +
                '}';
    }
}
