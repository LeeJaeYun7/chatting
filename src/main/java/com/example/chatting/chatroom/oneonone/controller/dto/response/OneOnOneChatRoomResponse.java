package com.example.chatting.chatroom.oneonone.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OneOnOneChatRoomResponse {

    private String roomUuid;
    private String otherMemberUuid;

    @Builder
    public OneOnOneChatRoomResponse(String roomUuid, String otherMemberUuid){
        this.roomUuid = roomUuid;
        this.otherMemberUuid = otherMemberUuid;
    }

    public static OneOnOneChatRoomResponse of(String roomUuid, String otherMemberUuid){
        return OneOnOneChatRoomResponse.builder()
                                       .roomUuid(roomUuid)
                                       .otherMemberUuid(otherMemberUuid)
                                       .build();
    }
}
