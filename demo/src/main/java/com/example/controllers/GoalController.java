package com.example.controllers;

import com.example.models.CourseGoal;
import com.example.models.dtos.GoalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface GoalController {
    public ResponseEntity<List<GoalDTO>> getAllGoals();
    public ResponseEntity<List<GoalDTO>> getGoalsForUser(@RequestHeader(name="Authorization") String token);
}
