package com.example.chatting.friend.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FriendResponse {

    private long memberId;
    private String name;

    @Builder
    public FriendResponse(long memberId, String name){
        this.memberId = memberId;
        this.name = name;
    }

    public static FriendResponse of(long memberId, String name){
        return FriendResponse.builder()
                             .memberId(memberId)
                             .name(name)
                             .build();
    }
}
