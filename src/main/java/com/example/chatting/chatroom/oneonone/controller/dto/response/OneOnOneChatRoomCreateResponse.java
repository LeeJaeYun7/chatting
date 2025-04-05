package com.example.chatting.chatroom.oneonone.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OneOnOneChatRoomCreateResponse {

    private String oneOnOneChatRoomUuid;

    @Builder
    public OneOnOneChatRoomCreateResponse(String oneOnOneChatRoomUuid){
        this.oneOnOneChatRoomUuid = oneOnOneChatRoomUuid;
    }

    public static OneOnOneChatRoomCreateResponse of(String oneOnOneChatRoomUuid){
        return OneOnOneChatRoomCreateResponse.builder()
                                             .oneOnOneChatRoomUuid(oneOnOneChatRoomUuid)
                                             .build();
    }
}
