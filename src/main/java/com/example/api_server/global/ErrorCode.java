package com.example.api_server.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NEED_LOGIN(HttpStatus.UNAUTHORIZED,"AUTH-000","로그인이 필요합니다."),
    EMPTY_USERNAME(HttpStatus.BAD_REQUEST,"AUTH-001","아이디는 필수 입력값입니다."),
    EMPTY_PASSWORD(HttpStatus.BAD_REQUEST, "AUTH-002","비밀번호는 필수 입력값입니다."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH-003", "아이디 또는 비밀번호를 잘못 입력했습니다."),
    CHECK_NOT_PERFORMED(HttpStatus.BAD_REQUEST,"AUTH-004","아이디 및 이메일 중복 확인이 필요합니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "AUTH-005", "이미 존재하는 아이디입니다."),

    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND,"ROOM-001", "방을 찾을 수 없습니다."),
    ROOM_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"ROOM-002","이미 존재하는 방 번호입니다.");

    private final HttpStatus httpStatus; // HTTP 상태 코드
    private final String code;           // 에러 코드
    private final String message;        // 에러 메시지
}
