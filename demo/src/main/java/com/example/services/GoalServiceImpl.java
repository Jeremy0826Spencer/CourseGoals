package com.example.services;

import com.example.daos.GoalDAO;
import com.example.models.CourseGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements  GoalService{

    private GoalDAO goalDAO;

    @Autowired
    public GoalServiceImpl(GoalDAO goalDAO) {
        this.goalDAO = goalDAO;
    }

    public List<CourseGoal> getAllGoals() {
        return goalDAO.findAll();
    }

    public List<CourseGoal> getGoalsForUser(int userId) {
        return goalDAO.findAllByUserId(userId);
    }
}
