package com.example.api_server.service;

import com.example.api_server.dto.request.LoginReqDto;
import com.example.api_server.dto.request.SignUpReqDto;
import com.example.api_server.global.response.ResponseDto;

public interface UserService {
    ResponseDto login(LoginReqDto loginReqDto); // LoginReqDto를 인자로 받도록 정의
    ResponseDto signUp(SignUpReqDto signUpReqDto);
    ResponseDto checkUsername(String username);
}
