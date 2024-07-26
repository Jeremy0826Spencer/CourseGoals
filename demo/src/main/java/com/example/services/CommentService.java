package com.example.services;

import com.example.models.dtos.CommentDTO;
import com.example.models.dtos.ReturnCommentDTO;

import java.util.List;

public interface CommentService {

    String createComment(CommentDTO comment);
    List<ReturnCommentDTO> getCommentsForGoal(Long goalId);
}
