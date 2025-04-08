package com.example.chatting.chat.group.participant.domain;

import com.example.chatting.chat.group.room.domain.enums.ParticipantRole;
import com.example.chatting.shared.entities.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "group_chat_participant", indexes = {
        @Index(name = "idx_group_room_id", columnList = "room_id"),
        @Index(name = "idx_member_id", columnList = "member_id")
})
@NoArgsConstructor
public class GroupChatParticipant extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_id", nullable = false)
    private long roomId;

    @Column(name = "member_id", nullable = false)
    private long memberId;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ParticipantRole role; // 역할 추가

    @Builder
    public GroupChatParticipant(long roomId, long memberId, ParticipantRole role) {
        this.roomId = roomId;
        this.memberId = memberId;
        this.joinedAt = LocalDateTime.now();
        this.role = role;
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public static GroupChatParticipant of(long roomId, long memberId, ParticipantRole role) {
        return GroupChatParticipant.builder()
                                   .roomId(roomId)
                                   .memberId(memberId)
                                   .role(role)
                                   .build();
    }
}
