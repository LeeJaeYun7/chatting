package com.example.chatting.chat.oneonone.message.application;

import com.example.chatting.chat.oneonone.message.domain.OneOnOneChatMessage;
import com.example.chatting.chat.oneonone.message.infrastructure.OneOnOneChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OneOnOneChatMessageService {

    private final OneOnOneChatMessageRepository oneOnOneChatMessageRepository;

    public LocalDateTime createChatMessage(long roomId, long senderId, long receiverId, String content) {
        LocalDateTime timestamp = LocalDateTime.now();
        OneOnOneChatMessage oneOnOneChatMessage = OneOnOneChatMessage.of(roomId, senderId,  receiverId, content, timestamp);
        oneOnOneChatMessageRepository.save(oneOnOneChatMessage); // MongoDB에 메시지 저장
        return timestamp;
    }

    // 예시: 채팅방의 모든 메시지 조회
    public List<OneOnOneChatMessage> getChatMessagesByRoomId(long roomId) {
        return oneOnOneChatMessageRepository.findByRoomId(roomId);
    }
}
