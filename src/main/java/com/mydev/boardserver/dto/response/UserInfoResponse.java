package com.mydev.boardserver.dto.response;

import com.mydev.boardserver.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private UserDTO userDTO;
}
