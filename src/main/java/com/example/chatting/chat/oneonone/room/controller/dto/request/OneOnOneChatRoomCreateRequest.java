package com.example.chatting.chat.oneonone.room.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OneOnOneChatRoomCreateRequest {
    private long otherMemberId;
}
