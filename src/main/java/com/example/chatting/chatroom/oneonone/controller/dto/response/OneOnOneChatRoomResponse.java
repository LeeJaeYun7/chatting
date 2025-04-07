package com.example.chatting.chatroom.oneonone.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OneOnOneChatRoomResponse {

    private long roomId;
    private long otherMemberId;

    @Builder
    public OneOnOneChatRoomResponse(long roomId, long otherMemberId){
        this.roomId = roomId;
        this.otherMemberId = otherMemberId;
    }

    public static OneOnOneChatRoomResponse of(long roomId, long otherMemberId){
        return OneOnOneChatRoomResponse.builder()
                                       .roomId(roomId)
                                       .otherMemberId(otherMemberId)
                                       .build();
    }
}
