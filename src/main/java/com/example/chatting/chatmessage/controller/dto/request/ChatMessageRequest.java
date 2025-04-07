package com.example.chatting.chatmessage.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatMessageRequest {

    private String roomId;     // 어떤 채팅방에 보낸 건지
    private String senderId;
    private String receiverId;
    private String content;    // 메시지 내용

    @Builder
    public ChatMessageRequest(String roomId, String senderId, String receiverId, String content) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }
}
