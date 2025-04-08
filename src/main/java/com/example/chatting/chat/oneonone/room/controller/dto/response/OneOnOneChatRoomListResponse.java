package com.example.chatting.chat.oneonone.room.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OneOnOneChatRoomListResponse {
    private List<OneOnOneChatRoomResponse> oneOnOneChatRoomList;

    @Builder
    public OneOnOneChatRoomListResponse(List<OneOnOneChatRoomResponse> oneOnOneChatRoomList){
        this.oneOnOneChatRoomList = oneOnOneChatRoomList;
    }

    public static OneOnOneChatRoomListResponse of(List<OneOnOneChatRoomResponse> oneOnOneChatRoomList){
        return OneOnOneChatRoomListResponse.builder()
                                           .oneOnOneChatRoomList(oneOnOneChatRoomList)
                                           .build();
    }
}

