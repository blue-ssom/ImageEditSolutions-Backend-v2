package com.example.api_server.user.service.impl;

import com.example.api_server.global.JwtUtil;
import com.example.api_server.user.dto.request.CheckUsernameReqDto;
import com.example.api_server.user.dto.request.LoginReqDto;
import com.example.api_server.user.dto.request.SignUpReqDto;
import com.example.api_server.user.entity.User;
import com.example.api_server.global.CustomException;
import com.example.api_server.global.response.ResponseDto;
import com.example.api_server.user.mapper.UserMapper;
import com.example.api_server.user.repository.UserRepository;
import com.example.api_server.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.api_server.global.ErrorCode;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseDto login(LoginReqDto loginReqDto) {

        User user = userRepository.findByUsername(loginReqDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!user.getPassword().equals(loginReqDto.getPassword())) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        // JWT 생성
        String token = jwtUtil.createToken(user.getId(), user.getUsername());

        // 토큰 반환
        return ResponseDto.token(token);
    }

    @Override
    public ResponseDto signUp(SignUpReqDto signUpReqDto){

        // 중복 확인 여부
        if (!Boolean.TRUE.equals(signUpReqDto.getIsChecked())) {
            throw new CustomException(ErrorCode.CHECK_NOT_PERFORMED);
        }

        // UserMapper를 사용하여 User 생성
        User user = UserMapper.mapToUser(signUpReqDto);
        userRepository.save(user);

        return ResponseDto.success("회원가입 성공");
    }

    @Override
    public ResponseDto checkUsername(CheckUsernameReqDto checkUsernameReqDto){

        // 중복 여부 확인
        boolean isAvailable = userRepository.existsByUsername(checkUsernameReqDto.getUsername());

        if(isAvailable) throw new CustomException(ErrorCode.DUPLICATE_USERNAME);

        return ResponseDto.success("사용 가능한 아이디입니다.");
    }
}
