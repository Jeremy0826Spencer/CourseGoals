package com.example.services;

import com.example.models.CourseGoal;
import com.example.models.dtos.GoalDTO;

import java.util.List;

public interface GoalService {
    public List<GoalDTO> getAllGoals();
    public List<GoalDTO> getGoalsForUser(String token);
    String createGoal(String token, GoalDTO goal);
}
