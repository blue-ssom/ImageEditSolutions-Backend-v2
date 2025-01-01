package com.example.api_server.room.service;

import com.example.api_server.global.response.ResponseDto;

public interface RoomService {
    // 방 만들기
    ResponseDto createRoom(Long userId, Integer roomNumber);

    // 방 입장하기
    ResponseDto joinRoom(Long userId, Integer roomNumber);
}
