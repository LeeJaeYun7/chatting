package com.example.chatting.friend.infrastructure;

import com.example.chatting.friend.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByMemberUuid(String memberUuid);
    Optional<Friend> findByMemberUuidAndFriendUuid(String memberUuid, String friendUuid);
}
