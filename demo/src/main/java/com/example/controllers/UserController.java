package com.example.controllers;

import com.example.models.dtos.ChangeProfileDTO;
import com.example.models.dtos.ReturnProfileDTO;
import com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API/V1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
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

    @DeleteMapping("user/myAccount")
    public ResponseEntity<String> deleteMyAccount(@RequestHeader(name="Authorization") String token){
        return new ResponseEntity<>(userService.deleteMyProfile(token), HttpStatus.OK);
    }
}
