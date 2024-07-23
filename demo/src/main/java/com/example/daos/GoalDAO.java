package com.example.daos;

import com.example.models.CourseGoal;
import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalDAO extends JpaRepository<CourseGoal, Integer> {
    List<CourseGoal> findAllByUserId(Long userId);
}
