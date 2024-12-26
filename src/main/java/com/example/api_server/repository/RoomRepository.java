package com.example.api_server.repository;

import com.example.api_server.entity.Room;
import com.example.api_server.entity.RoomParticipant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>  {
    Optional<Room> findByRoomNumber(Integer roomNumber);
}
