package com.example.chatting.chat.group.message.infrastructure;

import com.example.chatting.chat.group.message.domain.GroupChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupChatMessageRepository extends MongoRepository<GroupChatMessage, Long> {
    List<GroupChatMessage> findByRoomId(long roomId);
}
