package com.example.chatting.member.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberResponse {
    private long memberId;

    @Builder
    public MemberResponse(long memberId){
        this.memberId = memberId;
    }

    public static MemberResponse of(long memberId){
        return MemberResponse.builder()
                             .memberId(memberId)
                             .build();
    }
}
