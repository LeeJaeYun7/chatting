package com.example.chatting.chat.group.room.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class GroupChatRoomCreateRequest {

    private String name;
    private List<Long> participantIds;

    @Builder
    public GroupChatRoomCreateRequest(String name, List<Long> participantIds) {
        this.name = name;
        this.participantIds = participantIds;
    }

    public static GroupChatRoomCreateRequest of(String name, List<Long> participantIds) {
        return GroupChatRoomCreateRequest.builder()
                                         .name(name)
                                         .participantIds(participantIds)
                                         .build();
    }
}
