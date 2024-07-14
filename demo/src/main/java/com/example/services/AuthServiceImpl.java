package com.example.services;

import com.example.daos.RoleDAO;
import com.example.daos.UserDAO;
import com.example.jwt.JwtAuthResponse;
import com.example.jwt.JwtTokenProvider;
import com.example.models.Role;
import com.example.models.User;
import com.example.models.dtos.RegisterDTO;
import com.example.models.dtos.UserLoginDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
//This is the implementation class of the service for login and register
@Service
public class AuthServiceImpl implements AuthService{

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //Method used for register
    @Override
    public ResponseEntity<String> register(RegisterDTO registerDTO) {

        throwProperDataIntegrityViolation(registerDTO);

        User user = new User(registerDTO.getUsername(), passwordEncoder.encode(registerDTO.getPassword()), registerDTO.getFirstName(), registerDTO.getLastName(), registerDTO.getEmail());

        setRolesForUser(user);

        userDAO.save(user);

        return ResponseEntity.ok("User Registered Successfully.");
    }
    //Sets the role to user
    private void setRolesForUser(User user){
        Set<Role> roles = new HashSet<>();
        Role userRole = roleDAO.findByName("ROLE_USER");
        roles.add(userRole);
        user.setRoles(roles);
    }
    //Throws an exception with "Username already exists." if the username exists and "Email already exists." is the email exists.
    private void throwProperDataIntegrityViolation(RegisterDTO registerDTO){
        if(userDAO.existsByUsername(registerDTO.getUsername())){
            throw new DataIntegrityViolationException("Username already exists.");
        }
        if(userDAO.existsByEmail(registerDTO.getEmail())){
            throw new DataIntegrityViolationException("Email already exists.");
        }
    }


    @Override
    public JwtAuthResponse login(UserLoginDTO userLoginDTO) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDTO.getUsernameOrEmail(),
                userLoginDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Optional<User> userOptional = userDAO.findByUsernameOrEmail(userLoginDTO.getUsernameOrEmail(),
                userLoginDTO.getUsernameOrEmail());

        String role = null;
        if(userOptional.isPresent()){
            User loggedInUser = userOptional.get();
            Set<Role> roleSet = loggedInUser.getRoles();

            if(isUserAdmin(roleSet)){
                role = "ROLE_ADMIN";
            }
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);

        return jwtAuthResponse;
    }

    public boolean isUserAdmin(Set roleSet){
        if(roleSet.contains("ROLE_ADMIN")){
            return true;
        }
        return false;
    }
}
