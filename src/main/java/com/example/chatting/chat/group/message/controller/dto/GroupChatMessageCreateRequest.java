package com.example.chatting.chat.group.message.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class GroupChatMessageCreateRequest {
    private String roomId;
    private String senderId;
    private String content;
}
