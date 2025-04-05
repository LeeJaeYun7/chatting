package com.example.chatting.chatroom.oneonone.domain;


import com.example.chatting.commons.entities.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "one_on_one_chat_room")
@NoArgsConstructor
public class OneOnOneChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "member_id1")
    private long memberId1;

    @Column(name = "member_id2")
    private long memberId2;

    @Builder
    public OneOnOneChatRoom(long memberId1, long memberId2){
        this.memberId1 = memberId1;
        this.memberId2 = memberId2;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static OneOnOneChatRoom of(long memberId1, long memberId2){
        return OneOnOneChatRoom.builder()
                               .memberId1(memberId1)
                               .memberId2(memberId2)
                               .build();
    }
}
