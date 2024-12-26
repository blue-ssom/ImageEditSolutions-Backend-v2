package com.example.api_server.service;

import com.example.api_server.dto.request.JoinRoomReqDto;
import com.example.api_server.global.response.ResponseDto;

public interface RoomService {
    // 방 만들기
    ResponseDto createRoom(Long userId);

    // 방 입장하기
    ResponseDto joinRoom(JoinRoomReqDto joinRoomReqDto);
}
