package com.example.api_server.editing;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class RoomParticipantManager {
    private final Map<String, Set<String>> roomParticipants = new ConcurrentHashMap<>();

    public void addUserToRoom(String roomNumber, String sessionId) {
        roomParticipants.computeIfAbsent(roomNumber, key -> ConcurrentHashMap.newKeySet()).add(sessionId);
    }

    public void removeUserFromRoom(String sessionId) {
        roomParticipants.values().forEach(participants -> participants.remove(sessionId));
    }

    // 방 목록 반환
    public Set<String> getAllRooms() {
        return roomParticipants.keySet();
    }

    public Set<String> getParticipants(String roomNumber) {
        return roomParticipants.getOrDefault(roomNumber, Collections.emptySet());
    }
}
