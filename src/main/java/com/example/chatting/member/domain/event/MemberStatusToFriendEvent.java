package com.example.chatting.member.domain.event;

import lombok.Builder;

import java.util.List;

public class MemberStatusToFriendEvent {

    private List<String> friendIds;
    boolean isOnline;

    @Builder
    public MemberStatusToFriendEvent(List<String> friendIds, boolean isOnline) {
        this.friendIds = friendIds;
        this.isOnline = isOnline;
    }

    // of 메소드
    public static MemberStatusToFriendEvent of(List<String> friendIds, boolean isOnline) {
        return MemberStatusToFriendEvent.builder()
                                         .friendIds(friendIds)
                                         .isOnline(isOnline)
                                         .build();
    }
}
