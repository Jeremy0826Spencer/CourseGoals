package com.example.controllers;

import com.example.models.dtos.ChangeProfileDTO;
import com.example.models.dtos.ReturnProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface UserController {

    public ResponseEntity<ReturnProfileDTO> getUserProfile(@RequestHeader(name="Authorization") String token);
    public  ResponseEntity<String> updateWholeProfile(@RequestBody ChangeProfileDTO dto, @RequestHeader(name="Authorization") String token);
    public ResponseEntity<String> deleteMyAccount(@RequestHeader(name="Authorization") String token);
}
