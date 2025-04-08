package com.example.chatting.chat.group.message.domain;

import com.example.chatting.shared.entities.BaseTimeEntity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "group_chat_message")
@Getter
@NoArgsConstructor
public class GroupChatMessage extends BaseTimeEntity {

    @Id
    private ObjectId id;

    private long roomId;         // 그룹 채팅방 ID
    private long senderId;       // 보낸 사람 ID
    private String content;      // 메시지 내용
    private LocalDateTime timestamp;

    @Builder
    public GroupChatMessage(long roomId, long senderId, String content, LocalDateTime timestamp) {
        this.roomId = roomId;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static GroupChatMessage of(long roomId, long senderId, String content, LocalDateTime timestamp) {
        return GroupChatMessage.builder()
                               .roomId(roomId)
                               .senderId(senderId)
                               .content(content)
                               .timestamp(timestamp)
                               .build();
    }
}
