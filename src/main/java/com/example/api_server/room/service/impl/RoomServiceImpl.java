package com.example.api_server.room.service.impl;

import com.example.api_server.room.dto.request.CreateRoomReqDto;
import com.example.api_server.room.entity.Room;
import com.example.api_server.room.entity.RoomParticipant;
import com.example.api_server.global.CustomException;
import com.example.api_server.global.ErrorCode;
import com.example.api_server.global.response.ResponseDto;
import com.example.api_server.room.mapper.RoomMapper;
import com.example.api_server.room.repository.RoomParticipantRepository;
import com.example.api_server.room.repository.RoomRepository;
import com.example.api_server.room.service.RoomService;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomParticipantRepository roomParticipantRepository;

    // 방 만들기
    @Override
    public ResponseDto createRoom(Long userId, CreateRoomReqDto createRoomReqDto) {

        Integer roomNumber = createRoomReqDto.getRoomNumber();
        // 방 번호가 이미 존재하는지 확인
        if (roomRepository.existsByRoomNumber(roomNumber)) {
            throw new CustomException(ErrorCode.ROOM_ALREADY_EXISTS);
        }

        // 새로운 방 생성 및 저장
        Room room = RoomMapper.mapToRoom(userId, roomNumber);
        roomRepository.save(room);

        // RoomParticipant에 생성자 추가
        RoomParticipant participant = RoomMapper.mapToRoomParticipant(
                room.getRoomId(),
                userId
        );
        roomParticipantRepository.save(participant);

        // ResponseDto로 성공 응답 생성
        return ResponseDto.success("방 생성 성공");
    }

    // 방 입장하기
    @Override
    public  ResponseDto joinRoom(Long userId, Integer roomNumber) {
        // 방 존재 여부 확인
        Room room = roomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new CustomException(ErrorCode.ROOM_NOT_FOUND));

        // 방 참여자 데이터 조회
        Optional<RoomParticipant> existingParticipant = roomParticipantRepository.findByRoomIdAndUserId(
                room.getRoomId(), userId);

        if (existingParticipant.isPresent()) {
            // 이미 참여 중인 경우 updated_at 갱신
            RoomParticipant participant = existingParticipant.get();
            participant.setUpdatedAt(LocalDateTime.now());
            roomParticipantRepository.save(participant);
            return ResponseDto.success("참여 중인 방에 다시 연결");
        }

        // RoomParticipant에 사용자 추가
        RoomParticipant participant = RoomMapper.mapToRoomParticipant(
                room.getRoomId(),
                userId
        );
        roomParticipantRepository.save(participant);

        return ResponseDto.success("방 입장 성공");
    }

}


