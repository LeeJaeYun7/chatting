package com.example.chatting.chatroom.oneonone.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OneOnOneChatRoomListResponse {
    private List<String> oneOnOneChatRoomUuids;

    @Builder
    public OneOnOneChatRoomListResponse(List<String> oneOnOneChatRoomUuids){
        this.oneOnOneChatRoomUuids = oneOnOneChatRoomUuids;
    }

    public static OneOnOneChatRoomListResponse of(List<String> oneOnOneChatRoomUuids){
        return OneOnOneChatRoomListResponse.builder()
                                           .oneOnOneChatRoomUuids(oneOnOneChatRoomUuids)
                                           .build();
    }
}

