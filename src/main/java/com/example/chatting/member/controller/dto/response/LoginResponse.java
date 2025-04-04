package com.example.chatting.member.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LoginResponse {

    private long memberId;
    private String token;

    @Builder
    public LoginResponse(long memberId, String token){
        this.memberId = memberId;
        this.token = token;
    }

    public static LoginResponse of(long memberId, String token){
        return LoginResponse.builder()
                            .memberId(memberId)
                            .token(token)
                            .build();
    }
}
