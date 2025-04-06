package com.example.chatting.chatmessage.application;

import com.example.chatting.chatmessage.domain.ChatMessage;
import com.example.chatting.chatmessage.infrastructure.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    public void createChatMessage(String roomUuid, String senderUuid, String receiverUuid, String content) {
        LocalDateTime timestamp = LocalDateTime.now();
        ChatMessage chatMessage = ChatMessage.of(roomUuid, senderUuid, receiverUuid, content, timestamp);
        chatMessageRepository.save(chatMessage); // MongoDB에 메시지 저장
    }

    // 예시: 채팅방의 모든 메시지 조회
    public List<ChatMessage> getChatMessagesByRoomUuid(String roomUuid) {
        return chatMessageRepository.findByRoomUuid(roomUuid);
    }
}
