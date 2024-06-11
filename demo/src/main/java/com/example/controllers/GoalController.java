package com.example.controllers;

import com.example.models.CourseGoal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface GoalController {
    public ResponseEntity<List<CourseGoal>> getAllGoals();
    public ResponseEntity<List<CourseGoal>> getGoalsForUser(@RequestHeader(name="Authorization") String token);
}
