package com.example.api_server.auth.controller;

import com.example.api_server.auth.dto.request.CheckUsernameReqDto;
import com.example.api_server.auth.dto.request.LoginReqDto;
import com.example.api_server.auth.dto.request.SignUpReqDto;
import com.example.api_server.auth.entity.User;
import com.example.api_server.global.response.ResponseDto;
import com.example.api_server.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
public class AuthController {
    private final AuthService authService;


    @Autowired
    private ApplicationContext context;

    @GetMapping("/")
    public ResponseEntity<ResponseDto<String>> simpleGetResponse() {
        return ResponseEntity.ok(ResponseDto.success("OK"));
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginReqDto loginReqDto,  HttpSession session) {
        // 서비스 계층에서 로그인 검증
        User user = authService.login(loginReqDto);

        // 세션에 사용자 정보 저장
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());

        Long userId = (Long) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");
        String sessionId = session.getId(); // 세션 ID 가져오기
        System.out.println("세션에 저장된 정보: userId=" + userId + ", username=" + username + ", sessionId=" + sessionId);

        // 성공 응답 생성
        return ResponseEntity.ok(ResponseDto.success("로그인 성공"));
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@Valid @RequestBody SignUpReqDto signUpReqDto) {
        ResponseDto response = authService.signUp(signUpReqDto);
        return ResponseEntity.ok(response);
    }

    // 아이디 또는 이메일 중복확인
    @GetMapping("/check-username")
    public ResponseEntity<ResponseDto> checkUsername(@RequestParam CheckUsernameReqDto checkUsernameReqDto) {
        ResponseDto response = authService.checkUsername(checkUsernameReqDto);
        return ResponseEntity.ok(response);
    }
}
