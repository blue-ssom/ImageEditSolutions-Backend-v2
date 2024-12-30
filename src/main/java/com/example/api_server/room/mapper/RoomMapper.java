package com.example.api_server.room.mapper;

import com.example.api_server.room.entity.Room;
import com.example.api_server.room.entity.RoomParticipant;

public class RoomMapper {
    public static Room mapToRoom(Long userId, Integer roomNumber){
        return new Room(
                userId,       // 생성자의 userId
                roomNumber  // 방 번호
        );
    }

    public static RoomParticipant mapToRoomParticipant(Long roomId, Long userId){
        return new RoomParticipant(
                roomId,
                userId
        );
    }
}
