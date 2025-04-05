package com.example.chatting.member.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberResponse {
    private String uuid;

    @Builder
    public MemberResponse(String uuid){
        this.uuid = uuid;
    }

    public static MemberResponse of(String uuid){
        return MemberResponse.builder()
                             .uuid(uuid)
                             .build();
    }
}
