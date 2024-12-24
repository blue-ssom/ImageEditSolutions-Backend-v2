package com.example.api_server.controller;

import com.example.api_server.dto.request.LoginReqDto;
import com.example.api_server.dto.request.SignUpReqDto;
import com.example.api_server.global.response.ResponseDto;
import com.example.api_server.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<ResponseDto<String>> simpleGetResponse() {
        return ResponseEntity.ok(ResponseDto.success("OK"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginReqDto loginReqDto) {
        ResponseDto response = userService.login(loginReqDto);   // LoginReqDto 전달
        return ResponseEntity.ok(response); // 응답 반환
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody SignUpReqDto signUpReqDto) {
        ResponseDto response = userService.signUp(signUpReqDto);   // signUpReqDto 전달
        return ResponseEntity.ok(response); // 응답 반환
    }

    // 아이디 또는 이메일 중복확인
    @GetMapping("/check-username")
    public ResponseEntity<?> checkUsername(@RequestParam String username) {
        ResponseDto response = userService.checkUsername(username);
        return ResponseEntity.ok(response);
    }
}
