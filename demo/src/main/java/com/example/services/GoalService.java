package com.example.services;

import com.example.daos.GoalDAO;
import com.example.models.CourseGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalService {

    private GoalDAO goalDAO;

    @Autowired
    public GoalService(GoalDAO goalDAO) {
        this.goalDAO = goalDAO;
    }

    public List<CourseGoal> getAllGoals() {
        return goalDAO.findAll();
    }
}
