package com.example.services;


import com.example.models.dtos.ChangeProfileDTO;
import com.example.models.dtos.ReturnProfileDTO;

public interface UserService {

    ReturnProfileDTO getMyAccount(String token);
    String updateWholeProfile(ChangeProfileDTO dto, String token);
    String deleteMyProfile(String token);
}
