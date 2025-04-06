package com.example.chatting.chatroom.oneonone.domain;

import com.example.chatting.shared.entities.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "one_on_one_chat_room",
        indexes = {
                @Index(name = "idx_member_uuid1", columnList = "member_uuid1"),
                @Index(name = "idx_member_uuid2", columnList = "member_uuid2"),
                @Index(name = "idx_room_uuid", columnList = "room_uuid")  // roomUuid 인덱스 추가
        })
@NoArgsConstructor
public class OneOnOneChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_uuid")
    private String roomUuid;

    @Column(name = "member_uuid1")
    private String memberUuid1;

    @Column(name = "member_uuid2")
    private String memberUuid2;

    @Builder
    public OneOnOneChatRoom(String roomUuid, String memberUuid1, String memberUuid2){
        this.roomUuid = roomUuid;
        this.memberUuid1 = memberUuid1;
        this.memberUuid2 = memberUuid2;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static OneOnOneChatRoom of(String memberUuid1, String memberUuid2){
        return OneOnOneChatRoom.builder()
                               .roomUuid(UUID.randomUUID().toString())
                               .memberUuid1(memberUuid1)
                               .memberUuid2(memberUuid2)
                               .build();
    }
}
