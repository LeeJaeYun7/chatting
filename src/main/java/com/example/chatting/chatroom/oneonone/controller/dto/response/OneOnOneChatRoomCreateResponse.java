package com.example.chatting.chatroom.oneonone.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OneOnOneChatRoomCreateResponse {

    private long oneOnOneChatRoomId;

    @Builder
    public OneOnOneChatRoomCreateResponse(long oneOnOneChatRoomId){
        this.oneOnOneChatRoomId = oneOnOneChatRoomId;
    }

    public static OneOnOneChatRoomCreateResponse of(long roomId){
        return OneOnOneChatRoomCreateResponse.builder()
                                             .oneOnOneChatRoomId(roomId)
                                             .build();
    }
}
