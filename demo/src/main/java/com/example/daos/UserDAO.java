package com.example.daos;

import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String Email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    //counts public course goals
    @Query("SELECT COUNT(c) FROM CourseGoal c WHERE c.user.id = :userId AND c.privacy = 1")
    int countCourseGoalsByUserId(@Param("userId") Long userId);
    //gets all accounts that are friends with user
    @Query("SELECT u FROM User u JOIN u.friends f WHERE f.id = :userId")
    List<User> getUserFriends(@Param("userId") Long userId);
}
