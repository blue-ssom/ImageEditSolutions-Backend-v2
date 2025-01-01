package com.example.api_server.auth.mapper;

import com.example.api_server.auth.dto.request.SignUpReqDto;
import com.example.api_server.auth.entity.User;

public class UserMapper {
    public static User mapToUser(SignUpReqDto signUpReqDto) {
        return new User(
                signUpReqDto.getUsername(),
                signUpReqDto.getPassword()
        );
    }
}
