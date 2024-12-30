package com.example.api_server.user.service;

import com.example.api_server.user.dto.request.LoginReqDto;
import com.example.api_server.user.dto.request.SignUpReqDto;
import com.example.api_server.global.response.ResponseDto;

public interface UserService {
    ResponseDto login(LoginReqDto loginReqDto);
    ResponseDto signUp(SignUpReqDto signUpReqDto);
    ResponseDto checkUsername(String username);
}
