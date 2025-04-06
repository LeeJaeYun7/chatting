package com.example.chatting.chatroom.oneonone.infrastructure;

import com.example.chatting.chatroom.oneonone.domain.OneOnOneChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OneOnOneChatRoomRepository extends JpaRepository<OneOnOneChatRoom, Long> {
    @Query("SELECT o FROM OneOnOneChatRoom o WHERE o.memberUuid1 = :memberUuid1 AND o.memberUuid2 = :memberUuid2")
    Optional<OneOnOneChatRoom> findByMemberUuids(@Param("memberUuid1") String memberUuid1, @Param("memberUuid2") String memberUuid2);

    @Query("SELECT o FROM OneOnOneChatRoom o WHERE o.memberUuid1 = :memberUuid OR o.memberUuid2 = :memberUuid")
    List<OneOnOneChatRoom> findByMemberUuid(@Param("memberUuid") String memberUuid);
}
