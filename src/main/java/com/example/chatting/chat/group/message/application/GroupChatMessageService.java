package com.example.chatting.chat.group.message.application;

import com.example.chatting.chat.group.message.domain.GroupChatMessage;
import com.example.chatting.chat.group.message.infrastructure.GroupChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupChatMessageService {

    private final GroupChatMessageRepository groupChatMessageRepository;

    public LocalDateTime createGroupChatMessage(long roomId, long senderId, String content){
        LocalDateTime timestamp = LocalDateTime.now();
        GroupChatMessage groupChatMessage = GroupChatMessage.of(roomId, senderId, content, timestamp);
        groupChatMessageRepository.save(groupChatMessage);
        return timestamp;
    }

    // 예시: 그룹 채팅방의 모든 메시지 조회
    public List<GroupChatMessage> getGroupChatMessages(long roomId) {
        return groupChatMessageRepository.findByRoomId(roomId);
    }
}

