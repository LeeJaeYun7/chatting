package com.example.chatting.chatmessage.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChatMessageRequest {

    private String roomUuid;     // 어떤 채팅방에 보낸 건지
    private String senderUuid;
    private String receiverUuid;
    private String content;    // 메시지 내용

    @Builder
    public ChatMessageRequest(String roomUuid, String senderUuid, String receiverUuid, String content) {
        this.roomUuid = roomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
    }
}
