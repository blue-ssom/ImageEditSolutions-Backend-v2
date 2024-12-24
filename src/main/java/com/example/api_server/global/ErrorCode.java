package com.example.api_server.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH-001", "아이디 또는 비밀번호를 잘못 입력했습니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "AUTH-002", "이미 존재하는 아이디입니다."),
    EMPTY_USERNAME(HttpStatus.BAD_REQUEST, "AUTH-003", "아이디는 필수 입력값입니다.");

    private final HttpStatus httpStatus; // HTTP 상태 코드
    private final String code;           // 에러 코드
    private final String message;        // 에러 메시지
}
