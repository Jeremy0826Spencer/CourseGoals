package com.example.controllers;

import com.example.models.User;
import com.example.models.dtos.ChangeProfileDTO;
import com.example.models.dtos.FriendDTO;
import com.example.models.dtos.OutgoingUserDTO;
import com.example.models.dtos.ReturnProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {

    public ResponseEntity<ReturnProfileDTO> getUserProfile(String token);
    public  ResponseEntity<String> updateWholeProfile(ChangeProfileDTO dto, String token);
    public ResponseEntity<String> deleteMyAccount(String token);
    public ResponseEntity<List<OutgoingUserDTO>> getAllAccounts();
    ResponseEntity<List<FriendDTO>> getAllUsers();

    /*
        public ResponseEntity<String> lockAccount(Long userId);
        public ResponseEntity<String> unlockAccount(Long userId);
        */
    ResponseEntity<String> toggleAccount(Long userId);
    ResponseEntity<String> addFriend(String token, Long friendId);
    ResponseEntity<List<FriendDTO>> friendsList(String token);
}
