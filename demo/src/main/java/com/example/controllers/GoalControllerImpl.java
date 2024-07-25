package com.example.controllers;

import com.example.jwt.JwtTokenProvider;
import com.example.models.CourseGoal;
import com.example.models.dtos.FriendGoalDTO;
import com.example.models.dtos.GoalDTO;
import com.example.services.GoalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/V1/goals")
@CrossOrigin(origins = "http://localhost:3000")
public class GoalControllerImpl implements GoalController{

    private GoalService goalService;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public GoalControllerImpl(GoalService goalService, JwtTokenProvider jwtTokenProvider) {
        this.goalService = goalService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/admin/allGoals")
    public ResponseEntity<List<GoalDTO>> getAllGoals(){
        return ResponseEntity.ok(goalService.getAllGoals());
    }

    @GetMapping("/user/getUserGoals")
    public ResponseEntity<List<GoalDTO>> getGoalsForUser(@RequestHeader (name="Authorization") String token){
        return ResponseEntity.ok(goalService.getGoalsForUser(token));
    }

    @PostMapping("/user/goal")
    public ResponseEntity<String> createGoal(@Valid @RequestBody GoalDTO goal, @RequestHeader (name="Authorization") String token){
        return ResponseEntity.ok(goalService.createGoal(token, goal));
    }
    @Override
    @DeleteMapping("/user/{goalId}")
    public ResponseEntity<String> deleteGoal(@PathVariable int goalId) {
        return ResponseEntity.ok(goalService.deleteGoal(goalId));
    }

    @GetMapping("/user/{userId}/friendGoals")
    public ResponseEntity<List<FriendGoalDTO>> getFriendsGoals(@PathVariable Long userId){
        return ResponseEntity.ok(goalService.getGoalsForFriend(userId));
    }

}
