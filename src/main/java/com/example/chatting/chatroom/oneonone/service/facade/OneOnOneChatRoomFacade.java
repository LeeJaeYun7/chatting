package com.example.chatting.chatroom.oneonone.service.facade;

import com.example.chatting.chatroom.oneonone.controller.dto.response.OneOnOneChatRoomListResponse;
import com.example.chatting.chatroom.oneonone.domain.OneOnOneChatRoom;
import com.example.chatting.chatroom.oneonone.service.OneOnOneChatRoomService;
import com.example.chatting.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OneOnOneChatRoomFacade {

    private final MemberService memberService;
    private final OneOnOneChatRoomService oneOnOneChatRoomService;

    // 1:1 채팅방은 Facade 계층에서
    // 두 멤버 id 중 작은 것이 항상 앞에 오도록 변환
    public long createOneOnOneChatRoom(long memberId1, long memberId2){
        long smallerId = Math.min(memberId1, memberId2);
        long biggerId = Math.max(memberId1, memberId2);

        memberService.validateMember(smallerId);
        memberService.validateMember(biggerId);

        oneOnOneChatRoomService.validateOneOnOneChatRoom(smallerId, biggerId);

        return oneOnOneChatRoomService.createOneOnOneChatRoom(smallerId, biggerId);
    }

    public OneOnOneChatRoomListResponse readOneOnOneChatRooms(long memberId){
        memberService.validateMember(memberId);

        List<OneOnOneChatRoom> oneOnOneChatRoomList = oneOnOneChatRoomService.readOneOnOneChatRooms(memberId);
        List<Long> oneOnOneChatRoomIds = oneOnOneChatRoomList.stream()
                                                             .map(OneOnOneChatRoom::getId)
                                                             .toList();

        return OneOnOneChatRoomListResponse.of(oneOnOneChatRoomIds);
    }
}
