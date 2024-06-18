package com.example.services;

import com.example.daos.GoalDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GoalServiceTests {

    @Mock
    private GoalDAO goalDAO;
    @InjectMocks
    private GoalServiceImpl goalService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    /*
    @Test
    public void
    */
}
