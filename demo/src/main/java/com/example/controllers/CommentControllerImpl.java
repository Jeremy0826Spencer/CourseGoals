package com.example.controllers;

import com.example.models.dtos.CommentDTO;
import com.example.models.dtos.ReturnCommentDTO;
import com.example.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
