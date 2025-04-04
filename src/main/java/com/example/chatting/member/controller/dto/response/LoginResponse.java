package com.example.chatting.member.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponse {
    private String token;

    @Builder
    public LoginResponse(String token){
        this.token = token;
    }

    public static LoginResponse of(String token){
        return LoginResponse.builder()
                            .token(token)
                            .build();
    }
}
