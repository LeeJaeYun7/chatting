package com.example.chatting.member.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Builder;

@Getter
@NoArgsConstructor
public class MemberStatusChangeEvent {

    private String memberId;
    private boolean isOnline;

    @Builder
    public MemberStatusChangeEvent(String memberId, boolean isOnline) {
        this.memberId = memberId;
        this.isOnline = isOnline;
    }

    public static MemberStatusChangeEvent of(String memberId, boolean isOnline) {
        return MemberStatusChangeEvent.builder()
                                .memberId(memberId)
                                .isOnline(isOnline)
                                .build();
    }
}
