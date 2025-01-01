package com.example.api_server.room.controller;

import com.example.api_server.global.CustomException;
import com.example.api_server.global.ErrorCode;
import com.example.api_server.global.response.ResponseDto;
import com.example.api_server.room.dto.request.CreateRoomReqDto;
import com.example.api_server.room.service.RoomService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // 방 만들기
    @PostMapping
    public ResponseEntity<ResponseDto> createRoom(
            @Valid @RequestBody CreateRoomReqDto createRoomReqDto,
            HttpSession session) {

        // 세션에서 사용자 ID 가져오기
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new CustomException(ErrorCode.NEED_LOGIN);
        }
        // 세션에 저장된 userId 로그 확인
        System.out.println("세션에 저장된 userId: " + userId);

        // 방 생성 로직
        ResponseDto response = roomService.createRoom(userId, createRoomReqDto.getRoomNumber());
        return ResponseEntity.ok(response);
    }

    // 방 입장하기
    @PostMapping("/join/{roomNumber}")
    public ResponseEntity<ResponseDto<String>> joinRoom(@PathVariable Integer roomNumber, HttpSession session) {
        // 세션에서 사용자 ID 가져오기
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new CustomException(ErrorCode.NEED_LOGIN);
        }

        // 세션에 저장된 userId 로그 확인
        System.out.println("세션에 저장된 userId: " + userId);

        // 서비스 호출 및 응답 처리
        ResponseDto<String> response = roomService.joinRoom(userId, roomNumber);
        return ResponseEntity.ok(response);
    }

}
