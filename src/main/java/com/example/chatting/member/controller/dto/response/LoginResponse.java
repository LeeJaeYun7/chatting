package com.example.chatting.member.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponse {

    private String jwtToken;

    @Builder
    public LoginResponse(String jwtToken){
        this.jwtToken = jwtToken;
    }

    public static LoginResponse of(String jwtToken){
        return LoginResponse.builder()
                            .jwtToken(jwtToken)
                            .build();
    }
}
