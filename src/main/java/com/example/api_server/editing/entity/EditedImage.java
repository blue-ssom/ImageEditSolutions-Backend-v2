package com.example.api_server.editing.entity;

import com.example.api_server.room.entity.Room;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class EditedImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id") // 외래 키로 Room 엔티티와 연결
    private Room room;

    private String type;
    private String color;
    private int lineWidth;

    @ElementCollection // 컬렉션 타입을 JPA가 처리하도록 지정
    private List<Point> path; // 경로 데이터

    // 생성자
    public EditedImage(List<Point> path, String color, int lineWidth) {
        this.path = path;
        this.color = color;
        this.lineWidth = lineWidth;
    }
}
