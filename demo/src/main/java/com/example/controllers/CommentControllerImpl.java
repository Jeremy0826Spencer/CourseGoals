package com.example.controllers;

import com.example.models.dtos.CommentDTO;
import com.example.models.dtos.ReturnCommentDTO;
import com.example.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//This is the controller implementation for creating comments on other users goals.
//It also can get the comments for a goal which is used in the front end to display comments in a
//React map on each goal to show all of it's comments either in the users home page or on a friends page.
@RestController
@RequestMapping("/API/V1/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentControllerImpl implements CommentController{

    private CommentService commentService;

    public CommentControllerImpl(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @PostMapping("/user/comment")
    public ResponseEntity<String> createComment(@RequestBody CommentDTO comment) {
        return new ResponseEntity<String>(commentService.createComment(comment), HttpStatus.OK);
    }

    @Override
    @GetMapping("/user/comments/{goalId}")
    public ResponseEntity<List<ReturnCommentDTO>> getCommentsForGoal(@PathVariable Long goalId) {
        return new ResponseEntity<>(commentService.getCommentsForGoal(goalId), HttpStatus.OK);
    }
}
