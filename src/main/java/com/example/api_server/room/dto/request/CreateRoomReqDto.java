package com.example.api_server.room.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomReqDto {

    @NotBlank(message = "방 이름은 필수 입력값입니다.")
    private Integer roomNumber;


    public Integer getRoomNumber() {
        return roomNumber;
    }


}
