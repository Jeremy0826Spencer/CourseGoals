package com.example.services;

import com.example.daos.GoalDAO;
import com.example.jwt.JwtTokenProvider;
import com.example.models.CourseGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements  GoalService{

    private GoalDAO goalDAO;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public GoalServiceImpl(GoalDAO goalDAO, JwtTokenProvider jwtTokenProvider) {
        this.goalDAO = goalDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public List<CourseGoal> getAllGoals() {
        return goalDAO.findAll();
    }

    public List<CourseGoal> getGoalsForUser(String token) {
        String jwt = token.substring(7,token.length());
        int userId = jwtTokenProvider.getUserId(jwt);
        return goalDAO.findAllByUserId(userId);
    }
}
