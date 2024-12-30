package com.example.api_server.controller;

import com.example.api_server.entity.DrawData;
import com.example.api_server.service.impl.RoomServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class EditElementController {

    private final RoomServiceImpl roomServiceImpl;
    @MessageMapping("/edit/{roomId}") // 클라이언트가 "/app/edit/{roomId}"로 보낸 메시지를 처리
    @SendTo("/topic/edit/{roomId}")  // 구독 중인 클라이언트들에게 메시지 전송
    public DrawData handleEditMessage(@DestinationVariable String roomId, @Payload DrawData drawData) {
        System.out.println("Room ID: " + roomId + ", Draw Data: " + drawData);
        return drawData; // 클라이언트들에게 데이터 반환
    }
}
