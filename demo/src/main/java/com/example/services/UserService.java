package com.example.services;


import com.example.models.User;
import com.example.models.dtos.ChangeProfileDTO;
import com.example.models.dtos.OutgoingUserDTO;
import com.example.models.dtos.ReturnProfileDTO;

import java.util.List;

public interface UserService {

    ReturnProfileDTO getMyAccount(String token);
    String updateWholeProfile(ChangeProfileDTO dto, String token);
    String deleteMyProfile(String token);
    List<OutgoingUserDTO> getAllAccounts();
    void lockAccount(Long userId);
    void toggleAccountLock(Long userId);
    void unlockAccount(Long userId);
}
