package com.example.services;

import com.example.daos.RoleDAO;
import com.example.daos.UserDAO;
import com.example.jwt.JwtTokenProvider;
import com.example.models.Role;
import com.example.models.User;
import com.example.models.dtos.RegisterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthServiceTests {
    @Mock
    private UserDAO userDAO;
    @Mock
    private RoleDAO roleDAO;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @InjectMocks
    private AuthServiceImpl authService;
    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterSuccess(){
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("testUser");
        registerDTO.setFirstName("John");
        registerDTO.setLastName("Doe");
        registerDTO.setEmail("email@email.com");
        registerDTO.setPassword("pass");

        when(userDAO.existsByUsername(registerDTO.getUsername())).thenReturn(false);
        when(userDAO.existsByEmail(registerDTO.getEmail())).thenReturn(false);
        when(roleDAO.findByName("ROLE_USER")).thenReturn(new Role(2,"ROLE_USER"));
        when(passwordEncoder.encode(registerDTO.getPassword())).thenReturn("encodedPassword");

        ResponseEntity<String> response = authService.register(registerDTO);

        verify(userDAO, times(1)).save(any(User.class));
        assertEquals(ResponseEntity.ok("User Registered Successfully."), response);
    }

    @Test
    void testRegisterUsernameExists(){
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("testUser");
        registerDTO.setFirstName("John");
        registerDTO.setLastName("Doe");
        registerDTO.setEmail("email@email.com");
        registerDTO.setPassword("pass");

        when(userDAO.existsByUsername(registerDTO.getUsername())).thenReturn(true);

        DataIntegrityViolationException violationException = new DataIntegrityViolationException("Username already exists.");
        ResponseEntity<String> response = new ResponseEntity<>(violationException.getMessage(), HttpStatus.CONFLICT);
        assertEquals(ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists."), response);
        violationException = null;
    }

    @Test
    void testRegisterEmailExists(){
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("testUser");
        registerDTO.setFirstName("John");
        registerDTO.setLastName("Doe");
        registerDTO.setEmail("email@email.com");
        registerDTO.setPassword("pass");

        when(userDAO.existsByEmail(registerDTO.getEmail())).thenReturn(true);
        DataIntegrityViolationException violationException = new DataIntegrityViolationException("Email already exists.");
        ResponseEntity<String> response = new ResponseEntity<>(violationException.getMessage(), HttpStatus.CONFLICT);
        assertEquals(ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists."), response);
        violationException = null;

    }

}
