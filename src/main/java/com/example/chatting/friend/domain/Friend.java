package com.example.chatting.friend.domain;

import com.example.chatting.commons.entities.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "friend", indexes = @Index(name = "idx_uuid", columnList = "uuid"))
@NoArgsConstructor
public class Friend extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="uuid")
    private String uuid;

    @Column(name="friend_uuid")
    private String friendUuid;

    @Builder
    public Friend(String uuid, String friendUuid){
        this.uuid = uuid;
        this.friendUuid = friendUuid;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static Friend of(String uuid, String friendUuid){
        return Friend.builder()
                     .uuid(uuid)
                     .friendUuid(friendUuid)
                     .build();
    }
}
