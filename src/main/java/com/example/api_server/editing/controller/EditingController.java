package com.example.api_server.editing.controller;

import com.example.api_server.editing.entity.EditedImage;
import com.example.api_server.editing.service.EditingService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class EditingController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final EditingService editingService;

    @MessageMapping("/room/{roomNumber}/edit") // 클라이언트가 이 경로로 메시지를 보냄
    public void sendImageEditToAllClients(@DestinationVariable String roomNumber,
            @Payload EditedImage editedImage) {

//        String userId = (String) session.getAttribute("userId");

        System.out.println("joined room " + roomNumber);
        System.out.println("Received data: " + editedImage);

        // 그리기 데이터 추가 및 갱신
        editingService.addDrawingCache(roomNumber, editedImage);

        // 모든 클라이언트에게 편집된 이미지를 전송
        simpMessagingTemplate.convertAndSend("/topic/room/" + roomNumber + "/edit", editedImage);
    }

}
