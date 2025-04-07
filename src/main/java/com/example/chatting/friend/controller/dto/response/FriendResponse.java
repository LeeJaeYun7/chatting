package com.example.chatting.friend.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FriendResponse {

    private long friendId;
    private String name;

    @Builder
    public FriendResponse(long friendId, String name){
        this.friendId = friendId;
        this.name = name;
    }

    public static FriendResponse of(long friendId, String name){
        return FriendResponse.builder()
                             .friendId(friendId)
                             .name(name)
                             .build();
    }
}
