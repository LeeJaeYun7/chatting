package com.example.chatting.friend.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FriendCreateRequest {
    private long memberId;
    private long friendMemberId;
}
