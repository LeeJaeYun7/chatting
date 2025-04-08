package com.example.chatting.chat.group.room.infrastructure;

import com.example.chatting.chat.group.room.domain.GroupChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChatRoomRepository extends JpaRepository<GroupChatRoom, String> {
}
