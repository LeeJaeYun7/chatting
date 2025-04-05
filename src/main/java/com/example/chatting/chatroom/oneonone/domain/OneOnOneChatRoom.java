package com.example.chatting.chatroom.oneonone.domain;

import com.example.chatting.commons.entities.BaseTimeEntity;
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
                @Index(name = "idx_uuid1", columnList = "uuid1"),
                @Index(name = "idx_uuid2", columnList = "uuid2"),
                @Index(name = "idx_room_uuid", columnList = "room_uuid")  // roomUuid 인덱스 추가
        })
@NoArgsConstructor
public class OneOnOneChatRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_uuid")
    private String roomUuid;

    @Column(name = "uuid1")
    private String uuid1;

    @Column(name = "uuid2")
    private String uuid2;

    @Builder
    public OneOnOneChatRoom(String roomUuid, String uuid1, String uuid2){
        this.roomUuid = roomUuid;
        this.uuid1 = uuid1;
        this.uuid2 = uuid2;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static OneOnOneChatRoom of(String uuid1, String uuid2){
        return OneOnOneChatRoom.builder()
                               .roomUuid(UUID.randomUUID().toString())
                               .uuid1(uuid1)
                               .uuid2(uuid2)
                               .build();
    }
}
