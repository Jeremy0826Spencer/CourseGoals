package com.example.services;

import com.example.daos.RoleDAO;
import com.example.daos.UserDAO;
import com.example.jwt.JwtAuthResponse;
import com.example.jwt.JwtTokenProvider;
import com.example.models.Role;
import com.example.models.User;
import com.example.models.dtos.OutgoingUserDTO;
import com.example.models.dtos.RegisterDTO;
import com.example.models.dtos.UserLoginDTO;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public String register(RegisterDTO registerDTO) {
        if(userDAO.existsByUsername(registerDTO.getUsername())){
            throw new EntityExistsException();
        }

        if(userDAO.existsByEmail(registerDTO.getEmail())){
            throw new EntityExistsException();
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleDAO.findByName("ROLE_USER");
        roles.add(userRole);


        user.setRoles(roles);

        userDAO.save(user);

        return "User Registered Successfully.";
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
            Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();

            if(optionalRole.isPresent()){
                Role userRole = optionalRole.get();
                role = userRole.getName();
            }
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);

        return jwtAuthResponse;
    }
}
