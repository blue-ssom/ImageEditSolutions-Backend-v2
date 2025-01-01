package com.example.api_server.auth.service;

import com.example.api_server.auth.dto.request.CheckUsernameReqDto;
import com.example.api_server.auth.dto.request.LoginReqDto;
import com.example.api_server.auth.dto.request.SignUpReqDto;
import com.example.api_server.auth.entity.User;
import com.example.api_server.global.response.ResponseDto;

public interface AuthService {
    User login(LoginReqDto loginReqDto);
    ResponseDto signUp(SignUpReqDto signUpReqDto);
    ResponseDto checkUsername(CheckUsernameReqDto checkUsernameReqDto);
}
