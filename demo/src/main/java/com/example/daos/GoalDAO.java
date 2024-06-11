package com.example.daos;

import com.example.models.CourseGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalDAO extends JpaRepository<CourseGoal, Integer> {
    List<CourseGoal> findAllByUserId(int userId);
}
