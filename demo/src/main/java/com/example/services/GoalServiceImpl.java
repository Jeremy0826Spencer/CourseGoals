package com.example.services;

import com.example.daos.GoalDAO;
import com.example.daos.UserDAO;
import com.example.jwt.JwtTokenProvider;
import com.example.models.CourseGoal;
import com.example.models.User;
import com.example.models.dtos.FriendGoalDTO;
import com.example.models.dtos.GoalDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl implements  GoalService{

    final private GoalDAO goalDAO;
    final private JwtTokenProvider jwtTokenProvider;
    final private UserDAO userDAO;

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
        String jwt = token.substring(7);
        Long userId = jwtTokenProvider.getUserId(jwt);
        return goalDAO.findAllByUserId(userId).stream().map(GoalDTO::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<FriendGoalDTO> getGoalsForFriend(Long userId) {
        return goalDAO.findAllByUserId(userId).stream().map(this::convertToFriendGoalDTO).collect(Collectors.toList());
    }

    private FriendGoalDTO convertToFriendGoalDTO(CourseGoal cg){
        return new FriendGoalDTO(cg.getGoalId(), cg.getTitle(), cg.getBody());
    }

    @Override
    public String createGoal(String token, GoalDTO goal) {
        //calls the user DAO to find the user by id with the id extracted from the jwt from the request
        Optional<User> user = userDAO.findById(jwtTokenProvider.getUserId(token.substring(7)));
        if(user.isEmpty()){
            throw new EntityNotFoundException("This user does not exist.");
        }
        goalDAO.save(new CourseGoal(goal.getTitle(), goal.getBody(), user.get(), goal.getPrivacyEnum()));
        return "Goal has been created.";
    }
    @Override
    public String deleteGoal(Long goalId) {
        Optional<CourseGoal> optGoal = goalDAO.findById(goalId);
        if(optGoal.isPresent()) {
            Optional<User> user = userDAO.findById(getUserIdFromGoal(optGoal.get()));
            if(user.isPresent()) {
                removeFromUserAndDelete(user.get(), optGoal.get());
                return "Goal deleted";
            }
        }
        return "Problem occurred.";
    }

    private Long getUserIdFromGoal(CourseGoal g){
        User u = g.getUser();
        return u.getId();
    }

    private void removeFromUserAndDelete(User user, CourseGoal goal){
        List<CourseGoal> goals = user.getCourseGoals();
        goals.remove(goal);
        goalDAO.delete(goal);
    }
}
