package com.example.api_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.awt.Point;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DrawData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String color;
    private int lineWidth;

    @ElementCollection // 컬렉션 타입을 JPA가 처리하도록 지정
    private List<Point> path; // 경로 데이터

    @Column(name = "room_id", nullable = false)
    private String roomId; // 해당 데이터가 속한 방 ID
}
