package com.example.chatting.chatroom.oneonone.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OneOnOneChatRoomListResponse {
    private List<Long> oneOnOneChatRoomIds;

    @Builder
    public OneOnOneChatRoomListResponse(List<Long> oneOnOneChatRoomIds){
        this.oneOnOneChatRoomIds = oneOnOneChatRoomIds;
    }

    public static OneOnOneChatRoomListResponse of(List<Long> oneOnOneChatRoomIds){
        return OneOnOneChatRoomListResponse.builder()
                                             .oneOnOneChatRoomIds(oneOnOneChatRoomIds)
                                             .build();
    }
}
