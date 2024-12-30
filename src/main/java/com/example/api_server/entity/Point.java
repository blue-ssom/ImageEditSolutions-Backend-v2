package com.example.api_server.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable // JPA가 임베디드 타입으로 처리할 수 있도록 지정
public class Point {
    private int x;
    private int y;
}
