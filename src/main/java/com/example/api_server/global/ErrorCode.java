package com.example.api_server.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorCode {

    private final HttpStatus httpStatus; // HTTP 상태 코드
    private final String code;           // 에러 코드
    private final String message;        // 에러 메시지
}
