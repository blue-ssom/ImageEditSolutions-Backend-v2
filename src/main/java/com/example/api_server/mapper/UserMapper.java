package com.example.api_server.mapper;

import com.example.api_server.dto.request.SignUpReqDto;
import com.example.api_server.entity.User;

public class UserMapper {
    public static User mapToUser(SignUpReqDto signUpReqDto) {
        return new User(
                signUpReqDto.getUsername(),
                signUpReqDto.getPassword()
        );
    }
}
