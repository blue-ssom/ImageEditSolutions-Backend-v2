package com.example.api_server.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMPTY_USERNAME(HttpStatus.BAD_REQUEST,"AUTH-001","아이디는 필수 입력값입니다."),
    EMPTY_PASSWORD(HttpStatus.BAD_REQUEST, "AUTH-002","비밀번호는 필수 입력값입니다."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH-003", "아이디 또는 비밀번호를 잘못 입력했습니다."),
    CHECK_NOT_PERFORMED(HttpStatus.BAD_REQUEST,"AUTH-004","아이디 및 이메일 중복 확인이 필요합니다."),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "AUTH-005", "이미 존재하는 아이디입니다."),

    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND,"ROOM-001", "방을 찾을 수 없습니다."),

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "JWT-001", "유효하지 않은 JWT 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "JWT-002", "JWT 토큰이 만료되었습니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "JWT-003", "지원하지 않는 JWT 토큰입니다."),
    EMPTY_CLAIMS(HttpStatus.BAD_REQUEST, "JWT-004", "JWT 클레임 문자열이 비어있습니다."),
    UNEXPECTED_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "JWT-999", "예기치 않은 서버 오류");

    private final HttpStatus httpStatus; // HTTP 상태 코드
    private final String code;           // 에러 코드
    private final String message;        // 에러 메시지
}
