package com.example.api_server.global;

import com.example.api_server.global.response.ResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDto<Object>> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ResponseDto.error(
                        errorCode.getCode(),
                        errorCode.getMessage()
                ));
    }
}
