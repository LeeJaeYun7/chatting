package com.example.chatting.chat.oneonone.room.infrastructure;

import com.example.chatting.chat.oneonone.room.domain.OneOnOneChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OneOnOneChatRoomRepository extends JpaRepository<OneOnOneChatRoom, Long> {
    @Query("SELECT o FROM OneOnOneChatRoom o WHERE o.memberId1 = :memberId1 AND o.memberId2 = :memberId2")
    Optional<OneOnOneChatRoom> findByMemberIds(@Param("memberId1") long memberId1, @Param("memberId2") long memberId2);

    @Query("SELECT o FROM OneOnOneChatRoom o WHERE o.memberId1 = :memberId OR o.memberId2 = :memberId")
    List<OneOnOneChatRoom> findByMemberId(@Param("memberId") long memberId);

    @Query("SELECT o FROM OneOnOneChatRoom o WHERE o.roomId = :roomId")
    Optional<OneOnOneChatRoom> findByRoomId(@Param("roomId") long roomId);
}
