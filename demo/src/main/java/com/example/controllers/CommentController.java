package com.example.controllers;

import com.example.models.dtos.CommentDTO;
import com.example.models.dtos.ReturnCommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//This is the interface for CommentControllerImpl
//This interfaces implementation is used for comments made on a friends goals they post
public interface CommentController {

    @PostMapping("/user/comment")
    ResponseEntity<String> createComment(@RequestBody CommentDTO comment);

    @GetMapping("/user/comments")
    ResponseEntity<List<ReturnCommentDTO>> getCommentsForGoal(@PathVariable Long goalId);
}
