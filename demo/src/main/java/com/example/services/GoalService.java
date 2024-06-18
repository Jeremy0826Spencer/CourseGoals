package com.example.services;

import com.example.models.CourseGoal;

import java.util.List;

public interface GoalService {
    public List<CourseGoal> getAllGoals();
    public List<CourseGoal> getGoalsForUser(String token);

}
