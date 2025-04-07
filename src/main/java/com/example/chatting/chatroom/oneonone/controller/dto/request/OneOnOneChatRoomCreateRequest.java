package com.example.chatting.chatroom.oneonone.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OneOnOneChatRoomCreateRequest {
    private long otherMemberId;
}
