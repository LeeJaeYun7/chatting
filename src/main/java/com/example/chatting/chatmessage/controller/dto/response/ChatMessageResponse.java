package com.example.chatting.chatmessage.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ChatMessageResponse {

    private String senderUuid;    // 메시지를 보낸 사용자 ID
    private String receiverUuid;
    private String content;   // 메시지 내용
    private LocalDateTime timestamp; // 메시지 보낸 시각

    @Builder
    public ChatMessageResponse(String senderUuid, String receiverUuid, String content, LocalDateTime timestamp){
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
        this.timestamp = timestamp;
    }

    public static ChatMessageResponse of(String senderUuid, String receiverUuid, String content, LocalDateTime timestamp){
        return ChatMessageResponse.builder()
                                  .senderUuid(senderUuid)
                                  .receiverUuid(receiverUuid)
                                  .content(content)
                                  .timestamp(timestamp)
                                  .build();
    }
}
