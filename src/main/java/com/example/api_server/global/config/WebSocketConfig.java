package com.example.api_server.global.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new ImageEditWebSocketHandler(), "/ws") // 웹 소켓 서버 엔드포인트
                .setAllowedOrigins("*"); // CORS
    }

    private class ImageEditWebSocketHandler extends TextWebSocketHandler {
        private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

        // 클라이언트가 연결되었을 때
        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            System.out.println("Connected: " + session.getId());
        }

        // 클라이언트로부터 메시지를 수신했을 때
        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            System.out.println("Received: " + message.getPayload());
            session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
        }

        // 클라이언트 연결이 종료되었을 때
        @Override
        public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
            System.out.println("Disconnected: " + session.getId());
        }

    }
}
