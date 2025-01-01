package com.example.api_server.auth.service.impl;

import com.example.api_server.auth.dto.request.CheckUsernameReqDto;
import com.example.api_server.auth.dto.request.LoginReqDto;
import com.example.api_server.auth.dto.request.SignUpReqDto;
import com.example.api_server.auth.entity.User;
import com.example.api_server.global.CustomException;
import com.example.api_server.global.response.ResponseDto;
import com.example.api_server.auth.mapper.UserMapper;
import com.example.api_server.auth.repository.AuthRepository;
import com.example.api_server.auth.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.api_server.global.ErrorCode;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    @Override
    public User login(LoginReqDto loginReqDto) {

        User user = authRepository.findByUsername(loginReqDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!user.getPassword().equals(loginReqDto.getPassword())) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        return user;
    }

    @Override
    public ResponseDto signUp(SignUpReqDto signUpReqDto){

        // 중복 확인 여부
        if (!Boolean.TRUE.equals(signUpReqDto.getIsChecked())) {
            throw new CustomException(ErrorCode.CHECK_NOT_PERFORMED);
        }

        // UserMapper를 사용하여 User 생성
        User user = UserMapper.mapToUser(signUpReqDto);
        authRepository.save(user);

        return ResponseDto.success("회원가입 성공");
    }

    @Override
    public ResponseDto checkUsername(CheckUsernameReqDto checkUsernameReqDto){

        // 중복 여부 확인
        boolean isAvailable = authRepository.existsByUsername(checkUsernameReqDto.getUsername());

        if(isAvailable) throw new CustomException(ErrorCode.DUPLICATE_USERNAME);

        return ResponseDto.success("사용 가능한 아이디입니다.");
    }
}
