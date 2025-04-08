package com.example.chatting.chat.oneonone.message.infrastructure;

import com.example.chatting.chat.oneonone.message.domain.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
    // 특정 채팅방에 대한 메시지 목록 조회
    List<ChatMessage> findByRoomId(long roomId);
}
