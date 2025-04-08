package com.example.chatting.chat.group.room.domain;

import com.example.chatting.shared.entities.BaseTimeEntity;
import com.example.chatting.shared.utils.SnowFlakeGenerator;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "group_chat_room",
        indexes = {
                @Index(name = "idx_room_id", columnList = "room_id")  // roomId 인덱스 추가
        })
@NoArgsConstructor
public class GroupChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_id")
    private long roomId;

    @Column(name = "creator_id")
    private long creatorId;

    @Column(name = "name")
    private String name;

    @Builder
    public GroupChatRoom(long roomId, long creatorId, String name){
        this.roomId = roomId;
        this.creatorId = creatorId;
        this.name = name;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static GroupChatRoom of(long creatorId, String name){
        return GroupChatRoom.builder()
                            .roomId(SnowFlakeGenerator.createSnowFlake())
                            .creatorId(creatorId)
                            .name(name)
                            .build();
    }
}
