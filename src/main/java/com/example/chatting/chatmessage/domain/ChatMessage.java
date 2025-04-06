package com.example.chatting.chatmessage.domain;

import com.example.chatting.shared.entities.BaseTimeEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collection = "chat_message")  // MongoDB의 'chat_message' 컬렉션에 저장
@Getter
@NoArgsConstructor
public class ChatMessage extends BaseTimeEntity {

    @Id
    private String id;        // MongoDB에서 자동 생성되는 id 필드
    private String roomUuid;    // 채팅방 ID
    private String senderUuid;    // 메시지를 보낸 사용자 ID
    private String receiverUuid; // 메세지를 받은 사용자 ID
    private String content;   // 메시지 내용
    private LocalDateTime timestamp; // 메시지 보낸 시각

    @Builder
    public ChatMessage(String roomUuid, String senderUuid, String receiverUuid, String content, LocalDateTime timestamp) {
        this.roomUuid = roomUuid;
        this.senderUuid = senderUuid;
        this.receiverUuid = receiverUuid;
        this.content = content;
        this.timestamp = timestamp;

        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static ChatMessage of(String roomUuid, String senderUuid, String receiverUuid, String content, LocalDateTime timestamp){
        return ChatMessage.builder()
                          .roomUuid(roomUuid)
                          .senderUuid(senderUuid)
                          .receiverUuid(receiverUuid)
                          .content(content)
                          .timestamp(timestamp)
                          .build();
    }
}
