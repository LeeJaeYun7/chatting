package com.example.chatting.chat.group.participant.infrastructure;

import com.example.chatting.chat.group.participant.domain.GroupChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupChatParticipantRepository extends JpaRepository<GroupChatParticipant, Long> {

    public List<GroupChatParticipant> findByRoomId(long roomId);
}
