package com.example.api_server.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckUsernameReqDto {

    @NotBlank(message = "아이디는 필수 입력값입니다.")
    private String username;

}
