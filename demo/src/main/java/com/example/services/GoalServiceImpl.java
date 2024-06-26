package com.example.services;

import com.example.daos.GoalDAO;
import com.example.daos.UserDAO;
import com.example.jwt.JwtTokenProvider;
import com.example.models.CourseGoal;
import com.example.models.User;
import com.example.models.dtos.GoalDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl implements  GoalService{

    private GoalDAO goalDAO;
    private JwtTokenProvider jwtTokenProvider;
    private UserDAO userDAO;

    @Autowired
    public GoalServiceImpl(GoalDAO goalDAO, JwtTokenProvider jwtTokenProvider, UserDAO userDAO) {
        this.goalDAO = goalDAO;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDAO = userDAO;
    }

    @Override
    public List<GoalDTO> getAllGoals() {
        return goalDAO.findAll().stream().map(GoalDTO::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<GoalDTO> getGoalsForUser(String token) {
        String jwt = token.substring(7,token.length());
        int userId = jwtTokenProvider.getUserId(jwt);
        return goalDAO.findAllByUserId(userId).stream().map(GoalDTO::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public String createGoal(String token, GoalDTO goal) {
        String jwt = token.substring(7,token.length());
        int userId = jwtTokenProvider.getUserId(jwt);
        Optional<User> user = userDAO.findById(userId);
        if(user.isEmpty()){
            throw new EntityNotFoundException("This user does not exist.");
        }
        CourseGoal goalToSave = new CourseGoal(goal.getTitle(), goal.getBody(), user.get(), goal.getPrivacyEnum());
        Optional<CourseGoal> optionalCourseGoal = Optional.of(goalDAO.save(goalToSave));
        if(optionalCourseGoal.isEmpty()){
            throw new EntityNotFoundException("Goal not created.");
        }
        return "Goal has been created.";
    }
}
