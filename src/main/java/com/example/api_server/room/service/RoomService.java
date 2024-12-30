package com.example.api_server.room.service;

import com.example.api_server.global.response.ResponseDto;
import com.example.api_server.room.dto.request.JoinRoomReqDto;

public interface RoomService {
    // 방 만들기
    ResponseDto createRoom(Long userId);

    // 방 입장하기
    ResponseDto joinRoom(JoinRoomReqDto joinRoomReqDto);
}
