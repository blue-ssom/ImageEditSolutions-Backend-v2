package com.example.api_server.room.controller;

import com.example.api_server.global.response.ResponseDto;
import com.example.api_server.room.dto.request.JoinRoomReqDto;
import com.example.api_server.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    // 방 만들기
    @PostMapping
    public ResponseEntity<ResponseDto<Map<String, Integer>>> createRoom(@RequestBody Map<String, Object> request) {
        Long userId = Long.valueOf(request.get("userId").toString());
        ResponseDto<Map<String, Integer>> response = roomService.createRoom(userId);
        return ResponseEntity.ok(response);
    }

    // 방 입장하기
    @PostMapping("/join")
    public ResponseEntity<ResponseDto<String>> joinRoom(@Valid @RequestBody JoinRoomReqDto joinRoomReqDto) {
        ResponseDto response = roomService.joinRoom(joinRoomReqDto);
        return ResponseEntity.ok(response);
    }

}
