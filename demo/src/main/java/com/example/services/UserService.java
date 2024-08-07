package com.example.services;


import com.example.models.User;
import com.example.models.dtos.ChangeProfileDTO;
import com.example.models.dtos.FriendDTO;
import com.example.models.dtos.OutgoingUserDTO;
import com.example.models.dtos.ReturnProfileDTO;

import java.util.List;

public interface UserService {

    ReturnProfileDTO getMyAccount(String token);

    ReturnProfileDTO getUserAccount(Long userId);

    String updateWholeProfile(ChangeProfileDTO dto, String token);
    String deleteMyProfile(String token);
    List<OutgoingUserDTO> getAllAccounts();
    void lockAccount(Long userId);
    void toggleAccountLock(Long userId);
    void unlockAccount(Long userId);
    public String addFriend(String token, Long friendId);

    List<FriendDTO> getAllUsers();
    List<FriendDTO> getAllFriends(String token);

    String removeFriend(String token, Long friendId);
}
