package com.example.api_server.room.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinRoomReqDto {
    private Long userId;

    @NotNull(message = "방 번호는 필수 입력값입니다.")
    private Integer roomNumber;
}
