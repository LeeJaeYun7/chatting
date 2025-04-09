package com.example.chatting.chat.oneonone.message.domain;

import com.example.chatting.shared.entities.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "one_on_onechat_message")  // MongoDB의 'one_on_one_chat_message' 컬렉션에 저장
@Getter
@NoArgsConstructor
public class OneOnOneChatMessage extends BaseTimeEntity {

    @Id
    private ObjectId id;        // MongoDB에서 자동 생성되는 id 필드
    private long roomId;    // 채팅방 ID
    private long senderId;    // 메시지를 보낸 사용자 ID
    private long receiverId; // 메세지를 받은 사용자 ID
    private String content;   // 메시지 내용
    private LocalDateTime timestamp; // 메시지 보낸 시각

    @Builder
    public OneOnOneChatMessage(long roomId, long senderId, long receiverId, String content, LocalDateTime timestamp) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;

        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static OneOnOneChatMessage of(long roomId, long senderId, long receiverId, String content, LocalDateTime timestamp){
        return OneOnOneChatMessage.builder()
                                  .roomId(roomId)
                                  .senderId(senderId)
                                  .receiverId(receiverId)
                                  .content(content)
                                  .timestamp(timestamp)
                                  .build();
    }
}
