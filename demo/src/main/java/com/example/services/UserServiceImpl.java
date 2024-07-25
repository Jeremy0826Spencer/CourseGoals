package com.example.services;

import com.example.daos.UserDAO;
import com.example.jwt.JwtTokenProvider;
import com.example.models.User;
import com.example.models.dtos.ChangeProfileDTO;
import com.example.models.dtos.FriendDTO;
import com.example.models.dtos.OutgoingUserDTO;
import com.example.models.dtos.ReturnProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<User> user = userDAO.findById(getIdFromJWT(token));
        if (user.isPresent()) {
            return new ReturnProfileDTO(user.get().getUsername(), user.get().getFirstName(),
                    user.get().getLastName(), user.get().getEmail());
        }else throw new DataIntegrityViolationException("Could not find user.");
    }

    @Override
    public ReturnProfileDTO getUserAccount(Long userId) {
        Optional<User> user = userDAO.findById(userId);
        if (user.isPresent()) {
            return new ReturnProfileDTO(user.get().getUsername(), user.get().getFirstName(),
                    user.get().getLastName(), user.get().getEmail());
        }else throw new DataIntegrityViolationException("Could not find user.");
    }

    @Override
    public String updateWholeProfile(ChangeProfileDTO dto, String token) {
        Optional<User> optUser = userDAO.findById(getIdFromJWT(token));
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
        Optional<User> optionalUser = userDAO.findById(getIdFromJWT(token));
        if(optionalUser.isPresent()) {
            User user =  optionalUser.get();
            user.getRoles().clear();
            userDAO.save(user);

            userDAO.deleteById(user.getId());
            return "User deleted successfully.";
        }else throw new DataIntegrityViolationException("Could not find user");
    }

    @Override
    public List<OutgoingUserDTO> getAllAccounts() {
        return userDAO.findAll().stream()
                .map(OutgoingUserDTO::mapToOutUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void lockAccount(Long userId) {
        Optional<User> user = userDAO.findById(userId);
        if (user.isPresent()) {
            user.get().setAccountLocked(true);
            userDAO.save(user.get());
        }
    }
    @Override
    public void toggleAccountLock(Long userId) {
        Optional<User> user = userDAO.findById(userId);
        if (user.isPresent()) {
            user.get().setAccountLocked(!user.get().isAccountLocked());
            userDAO.save(user.get());
        }
    }
    @Override
    public void unlockAccount(Long userId) {
        Optional<User> user = userDAO.findById(userId);
        if (user.isPresent()) {
            user.get().setAccountLocked(false);
            userDAO.save(user.get());
        }
    }

    private Long getIdFromJWT(String token){
        String jwt = token.substring(7, token.length());
        return jwtTokenProvider.getUserId(jwt);
    }

    @Override
    public String addFriend(String token, Long friendId) {
        User user = userDAO.findById(getIdFromJWT(token)).get();
        User friend = userDAO.findById(friendId).get();

        user.getFriends().add(friend);
        friend.getFriends().add(user);

        userDAO.save(user);
        userDAO.save(friend);

        return "Friend Added.";
    }

    @Override
    public List<FriendDTO> getAllUsers() {
        return userDAO.findAll().stream()
                .map(this::convertToFriendDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendDTO> getAllFriends(String token) {
        return userDAO.getUserFriends(getIdFromJWT(token)).stream()
                .map(this::convertToFriendDTO)
                .collect(Collectors.toList());
    }

    private FriendDTO convertToFriendDTO(User user){
        return new FriendDTO(user.getId(), user.getUsername(), userDAO.countCourseGoalsByUserId(user.getId()));
    }
}
