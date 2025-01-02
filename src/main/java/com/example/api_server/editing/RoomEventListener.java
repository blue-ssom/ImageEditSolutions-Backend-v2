package com.example.api_server.editing;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
@RequiredArgsConstructor
public class RoomEventListener {
    private static final Logger logger = LoggerFactory.getLogger(RoomEventListener.class);
    private final RoomParticipantManager participantManager;

    // 구독 이벤트 처리
    @EventListener
    public void handleSubscriptionEvent(SessionSubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId(); // 세션 ID 가져오기

        // 메시지의 destination 확인
        String destination = headerAccessor.getDestination();

        // destination에서 roomNumber 추출 (/topic/room/{roomNumber}/edit)
        String roomNumber = extractRoomNumberFromDestination(destination);

        // 참여자 추가
        participantManager.addUserToRoom(roomNumber, sessionId);

        // 현재 방의 참여자 목록 로그 출력
        Set<String> participants = participantManager.getParticipants(roomNumber);
        logger.info("방 [{}] 참여자 목록: {}", roomNumber, participants);
    }

    // destination에서 roomNumber 추출하는 메서드
    private String extractRoomNumberFromDestination(String destination) {
        String prefix = "/topic/room/";
        String suffix = "/edit";

        if (destination.startsWith(prefix) && destination.endsWith(suffix)) {
            int startIndex = prefix.length();
            int endIndex = destination.length() - suffix.length();
            return destination.substring(startIndex, endIndex); // roomNumber 부분 반환
        }
        return null; // 유효하지 않은 destination
    }


    // 구독 해제 이벤트 처리
    @EventListener
    public void handleUnsubscribeEvent(SessionUnsubscribeEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();

        // 참여자 제거
        participantManager.removeUserFromRoom(sessionId);

        // 각 방의 참여자 목록 확인 (갱신된 상태로)
        participantManager.getAllRooms().forEach(room -> {
            Set<String> participants = participantManager.getParticipants(room);
            logger.info("구독 해제: 방 [{}] 참여자 목록: {}", room, participants);
        });
    }
}
