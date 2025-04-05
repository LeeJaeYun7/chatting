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
    @Query("SELECT o FROM OneOnOneChatRoom o WHERE o.uuid1 = :uuid1 AND o.uuid2 = :uuid2")
    Optional<OneOnOneChatRoom> findByUuids(@Param("uuid1") String uuid1, @Param("uuid2") String uuid2);

    @Query("SELECT o FROM OneOnOneChatRoom o WHERE o.uuid1 = :uuid OR o.uuid2 = :uuid")
    List<OneOnOneChatRoom> findByUuid(@Param("uuid") String uuid);
}
