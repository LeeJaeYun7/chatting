package com.example.chatting.friend.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FriendResponse {

    private String friendUuid;
    private String name;

    @Builder
    public FriendResponse(String friendUuid, String name){
        this.friendUuid = friendUuid;
        this.name = name;
    }

    public static FriendResponse of(String friendUuid, String name){
        return FriendResponse.builder()
                             .friendUuid(friendUuid)
                             .name(name)
                             .build();
    }
}
