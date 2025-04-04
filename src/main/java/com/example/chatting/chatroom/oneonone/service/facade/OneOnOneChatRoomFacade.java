package com.example.chatting.chatroom.oneonone.service.facade;

import com.example.chatting.chatroom.oneonone.service.OneOnOneChatRoomService;
import com.example.chatting.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OneOnOneChatRoomFacade {

    private final MemberService memberService;
    private final OneOnOneChatRoomService oneOnOneChatRoomService;

    public long createOneOnOneChatRoom(long memberId1, long memberId2){
        memberService.validateMember(memberId2);
        oneOnOneChatRoomService.validateOneOnOneChatRoom(memberId1, memberId2);

        return oneOnOneChatRoomService.createOneOnOneChatRoom(memberId1, memberId2);
    }
}
