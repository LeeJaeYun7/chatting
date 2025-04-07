package com.example.chatting.friend.domain;

import com.example.chatting.shared.entities.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "friend", indexes = @Index(name = "idx_member_id", columnList = "member_id"))
@NoArgsConstructor
public class Friend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="member_id")
    private long memberId;

    @Column(name="friend_id")
    private long friendId;

    @Builder
    public Friend(long memberId, long friendId){
        this.memberId = memberId;
        this.friendId = friendId;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static Friend of(long memberId, long friendId){
        return Friend.builder()
                     .memberId(memberId)
                     .friendId(friendId)
                     .build();
    }
}
