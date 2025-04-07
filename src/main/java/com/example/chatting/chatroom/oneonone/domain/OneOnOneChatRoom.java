package com.example.chatting.chatroom.oneonone.domain;

import com.example.chatting.shared.entities.BaseTimeEntity;
import com.example.chatting.shared.utils.SnowFlakeGenerator;
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
                @Index(name = "idx_member_id1", columnList = "member_id1"),
                @Index(name = "idx_member_id2", columnList = "member_id2"),
                @Index(name = "idx_room_id", columnList = "room_id")  // roomUuid 인덱스 추가
        })
@NoArgsConstructor
public class OneOnOneChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_id")
    private long roomId;

    @Column(name = "member_id1")
    private long memberId1;

    @Column(name = "member_id2")
    private long memberId2;

    @Builder
    public OneOnOneChatRoom(long roomId, long memberId1, long memberId2){
        this.roomId = roomId;
        this.memberId1 = memberId1;
        this.memberId2 = memberId2;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static OneOnOneChatRoom of(long memberId1, long memberId2){
        return OneOnOneChatRoom.builder()
                               .roomId(SnowFlakeGenerator.createSnowFlake())
                               .memberId1(memberId1)
                               .memberId2(memberId2)
                               .build();
    }
}
