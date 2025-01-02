package com.example.api_server.room.service;

import com.example.api_server.global.response.ResponseDto;
import com.example.api_server.room.dto.request.CreateRoomReqDto;

public interface RoomService {
    // 방 만들기
    ResponseDto createRoom(Long userId, CreateRoomReqDto createRoomReqDto);

    // 방 입장하기
    ResponseDto joinRoom(Long userId, Integer roomNumber);
}
