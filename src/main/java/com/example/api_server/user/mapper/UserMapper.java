package com.example.api_server.user.mapper;

import com.example.api_server.user.dto.request.SignUpReqDto;
import com.example.api_server.user.entity.User;

public class UserMapper {
    public static User mapToUser(SignUpReqDto signUpReqDto) {
        return new User(
                signUpReqDto.getUsername(),
                signUpReqDto.getPassword()
        );
    }
}
