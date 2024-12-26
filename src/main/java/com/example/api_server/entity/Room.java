package com.example.api_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId; // Primary Key

    @Column(name = "user_id")
    private Long userId; // 방 생성자의 ID

    @Column(name = "room_number")
    private Integer roomNumber; // 방 번호

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt; // 생성 시간

    public Room(Long userId, Integer roomNumber) {
        this.userId = userId;
        this.roomNumber = roomNumber;
    }
}
