package com.example.api_server.user.service.impl;

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

    @Override
    public ResponseDto login(LoginReqDto loginReqDto) {

        User user = userRepository.findByUsername(loginReqDto.getUsername())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (!user.getPassword().equals(loginReqDto.getPassword())) {
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }

        return ResponseDto.success("로그인 성공");
    }

    @Override
    public ResponseDto signUp(SignUpReqDto signUpReqDto){
        // 중복된 username 체크
        if (userRepository.existsByUsername(signUpReqDto.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

        // UserMapper를 사용하여 User 생성
        User user = UserMapper.mapToUser(signUpReqDto);
        userRepository.save(user);

        return ResponseDto.success("회원가입 성공");
    }

    @Override
    public ResponseDto checkUsername(String username){
        // 빈 값 검증
        if (username == null || username.isBlank()) {
            throw new CustomException(ErrorCode.EMPTY_USERNAME);
        }

        // 중복 여부 확인
        boolean isAvailable = userRepository.existsByUsername(username);

        if(isAvailable) throw new CustomException(ErrorCode.DUPLICATE_USERNAME);

        return ResponseDto.success("사용 가능한 아이디입니다.");
    }
}
