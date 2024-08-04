package com.example.services;

import com.example.daos.GoalDAO;
import com.example.daos.UserDAO;
import com.example.jwt.JwtTokenProvider;
import com.example.models.CourseGoal;
import com.example.models.User;
import com.example.models.dtos.GoalDTO;
import com.example.models.enums.PrivacyEnum;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GoalServiceTests {

    @Mock
    private UserDAO userDAO;
    @Mock
    private GoalDAO goalDAO;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @InjectMocks
    private GoalServiceImpl goalService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ifUserOptEmpty() {
        String token = "someJwtToken";
        GoalDTO goalDTO = new GoalDTO(1L,"title", "body", PrivacyEnum.PUBLIC);

        when(userDAO.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            goalService.createGoal(token, goalDTO);
        });
    }

    @Test
    void GoalCreatedSuccessfully() {
        String token = "someJwtToken";
        GoalDTO goalDTO = new GoalDTO(1L,"title", "body", PrivacyEnum.PUBLIC);

        User mockUser = new User("jer", "pass", "jer", "spen", "j@g.com");
        CourseGoal mockGoal = new CourseGoal("title", "body", mockUser, PrivacyEnum.PUBLIC);

        when(userDAO.findById(any())).thenReturn(Optional.of(mockUser));
        when(goalDAO.save(any(CourseGoal.class))).thenReturn(mockGoal);

        assertEquals(
                "Goal has been created.",
            goalService.createGoal(token, goalDTO)
        );
    }

    /*
    @Test
    public void
    */
}
