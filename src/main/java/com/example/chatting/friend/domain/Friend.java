package com.example.chatting.friend.domain;

import com.example.chatting.commons.entities.BaseTimeEntity;
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

    @Column(name="friend_member_id")
    private long friendMemberId;

    @Builder
    public Friend(long memberId, long friendMemberId){
        this.memberId = memberId;
        this.friendMemberId = friendMemberId;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static Friend of(long memberId, long friendMemberId){
        return Friend.builder()
                     .memberId(memberId)
                     .friendMemberId(friendMemberId)
                     .build();
    }
}
