package com.example.services;

import com.example.daos.GoalDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GoalServiceTests {

    private GoalDAO goalDAO;
    private GoalService goalService;

    @BeforeEach
    public void setup(){
        goalDAO = Mockito.mock(GoalDAO.class);
        goalService = new GoalServiceImpl(goalDAO);
    }
    /*
    @Test
    public void
    */
}
