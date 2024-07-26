package com.example.services;

import com.example.daos.CommentDAO;
import com.example.daos.GoalDAO;
import com.example.models.Comment;
import com.example.models.CourseGoal;
import com.example.models.dtos.CommentDTO;
import com.example.models.dtos.GoalDTO;
import com.example.models.dtos.ReturnCommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private CommentDAO commentDAO;

    private GoalDAO goalDAO;

    public CommentServiceImpl(CommentDAO commentDAO, GoalDAO goalDAO) {
        this.commentDAO = commentDAO;
        this.goalDAO = goalDAO;
    }

    @Override
    public String createComment(CommentDTO comment) {
        Optional<CourseGoal> goal = goalDAO.findById(comment.getGoalId());
        if(goal.isPresent()) {
            commentDAO.save(new Comment(comment.getCommentText(), goal.get()));
            return "Comment Created.";
        }
        return "Failed.";
    }

    @Override
    public List<ReturnCommentDTO> getCommentsForGoal(Long goalId) {
        return commentDAO.findAllByGoalGoalId(goalId).stream()
                .map(this::convertToCommentDTO)
                .collect(Collectors.toList());
    }

    private ReturnCommentDTO convertToCommentDTO(Comment comment){
        return new ReturnCommentDTO(comment.getCommentId(), comment.getCommentText(), comment.getGoal().getGoalId());
    }
}
