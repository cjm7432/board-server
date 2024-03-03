package com.mydev.boardserver.service;

import com.mydev.boardserver.dto.UserDTO;
public interface UserService {

    void register(UserDTO userDTO);

    UserDTO login(String userId, String password);

    boolean isDuplicatedId(String userId);

    UserDTO getUserInfo(String userId);

    void updatePassword(String userId, String beforePassword, String afterPassword);

    void deleteId(String userId, String passWord);
}
