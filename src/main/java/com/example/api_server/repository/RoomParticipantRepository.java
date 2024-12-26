package com.example.api_server.repository;

import com.example.api_server.entity.RoomParticipant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomParticipantRepository extends JpaRepository<RoomParticipant,Long> {

    Optional<RoomParticipant> findByRoomIdAndUserId(Long roomId, Long userId);
}
