package com.example.api_server.editing.service;

import com.example.api_server.editing.entity.EditedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class EditingService {
    // 방 별로 그리기 데이터를 저장할 맵
    private final Map<String, List<EditedImage>> drawingCache = new HashMap<>();

    // 그리기 데이터를 추가하는 메서드
    public void addDrawingCache(String roomNumber, EditedImage request) {
        // 해당 방에 그리기 캐시가 존재하는지 확인
        List<EditedImage> roomDrawingCache = drawingCache.getOrDefault(roomNumber, new ArrayList<>());

        // 그리기 요청을 캐시에 추가
        roomDrawingCache.add(request);

        // 캐시 업데이트
        drawingCache.put(roomNumber, roomDrawingCache);
    }

    public List<EditedImage> getDrawingCache(Integer roomNumber) {
        return drawingCache.get(roomNumber);
    }

}
