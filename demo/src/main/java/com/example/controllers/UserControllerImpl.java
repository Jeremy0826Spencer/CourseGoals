package com.example.controllers;

import com.example.models.dtos.ChangeProfileDTO;
import com.example.models.dtos.OutgoingUserDTO;
import com.example.models.dtos.ReturnProfileDTO;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/API/V1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserControllerImpl implements UserController   {

    UserService userService;

    @Autowired
    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/myAccount")
    public ResponseEntity<ReturnProfileDTO> getUserProfile(@RequestHeader(name="Authorization") String token){
        return new ResponseEntity<>(userService.getMyAccount(token), HttpStatus.OK);
    }

    @PutMapping("/user/myAccount")
    public  ResponseEntity<String> updateWholeProfile(@RequestBody ChangeProfileDTO dto, @RequestHeader(name="Authorization") String token){
        return new ResponseEntity<>(userService.updateWholeProfile(dto, token), HttpStatus.OK);
    }

    @DeleteMapping("/user/myAccount")
    public ResponseEntity<String> deleteMyAccount(@RequestHeader(name="Authorization") String token){
        return new ResponseEntity<>(userService.deleteMyProfile(token), HttpStatus.OK);
    }

    @Override
    @GetMapping("/admin/accounts")
    public ResponseEntity<List<OutgoingUserDTO>> getAllAccounts() {
        return new ResponseEntity<>(userService.getAllAccounts(), HttpStatus.OK);
    }
    /*
    @Override
    @PatchMapping("/admin/{userId}/lock")
    public ResponseEntity<String> lockAccount(@PathVariable Long userId) {
        userService.lockAccount(userId);
        return new ResponseEntity<>("Account Locked", HttpStatus.OK);
    }

    @Override
    @PatchMapping("/admin/{userId}/unlock")
    public ResponseEntity<String> unlockAccount(@PathVariable Long userId) {
        userService.unlockAccount(userId);
        return new ResponseEntity<>("Account Unlocked", HttpStatus.OK);
    }
    */
    @Override
    @PatchMapping("/admin/{userId}/toggle")
    public ResponseEntity<String> toggleAccount(@PathVariable Long userId) {
        userService.toggleAccountLock(userId);
        return new ResponseEntity<>("Account lock changed", HttpStatus.OK);
    }
}
