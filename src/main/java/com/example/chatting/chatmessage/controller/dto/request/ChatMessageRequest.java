package com.example.chatting.chatmessage.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatMessageRequest {

    private long roomId;     // 어떤 채팅방에 보낸 건지
    private long senderId;
    private long receiverId;
    private String content;    // 메시지 내용

    @Builder
    public ChatMessageRequest(long roomId, long senderId, long receiverId, String content) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }
}
