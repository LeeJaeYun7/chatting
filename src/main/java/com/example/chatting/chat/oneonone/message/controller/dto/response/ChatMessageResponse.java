package com.example.chatting.chat.oneonone.message.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ChatMessageResponse {

    private long senderId;    // 메시지를 보낸 사용자 ID
    private long receiverId;
    private String content;   // 메시지 내용
    private LocalDateTime timestamp; // 메시지 보낸 시각

    @Builder
    public ChatMessageResponse(long senderId, long receiverId, String content, LocalDateTime timestamp){
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public static ChatMessageResponse of(long senderId, long receiverId, String content, LocalDateTime timestamp){
        return ChatMessageResponse.builder()
                                  .senderId(senderId)
                                  .receiverId(receiverId)
                                  .content(content)
                                  .timestamp(timestamp)
                                  .build();
    }
}
