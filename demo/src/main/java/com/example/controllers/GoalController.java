package com.example.controllers;

import com.example.models.CourseGoal;
import com.example.services.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/V1/")
@CrossOrigin
public class GoalController {

    private GoalService goalService;

    @Autowired
    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping("/allGoals")
    public ResponseEntity<List<CourseGoal>> getAllGoals(){
        return ResponseEntity.ok(goalService.getAllGoals());
    }

}
