package com.example.services;

import com.example.daos.UserDAO;
import com.example.jwt.JwtTokenProvider;
import com.example.models.User;
import com.example.models.dtos.ChangeProfileDTO;
import com.example.models.dtos.ReturnProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ReturnProfileDTO getMyAccount(String token) {
        String jwt = token.substring(7, token.length());
        Long userId = jwtTokenProvider.getUserId(jwt);
        Optional<User> user = userDAO.findById(userId);
        if (user.isPresent()) {
            return new ReturnProfileDTO(user.get().getUsername(), user.get().getFirstName(),
                    user.get().getLastName(), user.get().getEmail());
        }else throw new DataIntegrityViolationException("Could not find user.");
    }

    @Override
    public String updateWholeProfile(ChangeProfileDTO dto, String token) {
        String jwt = token.substring(7, token.length());
        Long userId = jwtTokenProvider.getUserId(jwt);
        Optional<User> optUser = userDAO.findById(userId);
        if((userDAO.existsByEmail(dto.getEmail()) && (!dto.getEmail().equals(optUser.get().getEmail())))){
            throw new DataIntegrityViolationException("Email already exists.");
        }
        if(optUser.isPresent()){
            User user = optUser.get();
            user.setFirstName(dto.getFirstName());
            user.setLastName(dto.getLastName());
            user.setEmail(dto.getEmail());
            userDAO.save(user);
            return "User updated.";

        }
        else throw new DataIntegrityViolationException("Could not find user.");
    }

    @Override
    public String deleteMyProfile(String token) {
        String jwt = token.substring(7, token.length());
        Long userId = jwtTokenProvider.getUserId(jwt);
        Optional<User> optionalUser = userDAO.findById(userId);
        if(optionalUser.isPresent()) {
            User user =  optionalUser.get();
            user.getRoles().clear();
            userDAO.save(user);

            userDAO.deleteById(userId);
            return "User deleted successfully.";
        }else throw new DataIntegrityViolationException("Could not find user");
    }
}
