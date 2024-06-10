package com.example.daos;

import com.example.models.CourseGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalDAO extends JpaRepository<CourseGoal, Integer> {
}
