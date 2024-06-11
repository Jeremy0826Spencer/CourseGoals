package com.example.controllers;

import com.example.jwt.JwtTokenProvider;
import com.example.models.CourseGoal;
import com.example.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/V1/goals")
@CrossOrigin
public class GoalControllerImpl implements GoalController{

    private GoalService goalService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public GoalControllerImpl(GoalService goalService, JwtTokenProvider jwtTokenProvider) {
        this.goalService = goalService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/admin/allGoals")
    public ResponseEntity<List<CourseGoal>> getAllGoals(){
        return ResponseEntity.ok(goalService.getAllGoals());
    }

    @GetMapping("/user/getUserGoals")
    public ResponseEntity<List<CourseGoal>> getGoalsForUser(@RequestHeader (name="Authorization") String token){
        String jwt = token.substring(7,token.length());
        int userId = jwtTokenProvider.getUserId(jwt);
        return ResponseEntity.ok(goalService.getGoalsForUser(userId));
    }

}
