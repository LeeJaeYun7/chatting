package com.example.chatting.chat.oneonone.message.infrastructure;

import com.example.chatting.chat.oneonone.message.domain.OneOnOneChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OneOnOneChatMessageRepository extends MongoRepository<OneOnOneChatMessage, String> {
    // 특정 채팅방에 대한 메시지 목록 조회
    List<OneOnOneChatMessage> findByRoomId(long roomId);
}
