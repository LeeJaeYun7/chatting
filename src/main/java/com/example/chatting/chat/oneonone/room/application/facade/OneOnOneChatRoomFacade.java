package com.example.chatting.chat.oneonone.room.application.facade;

import com.example.chatting.chat.oneonone.room.application.OneOnOneChatRoomService;
import com.example.chatting.chat.oneonone.room.controller.dto.response.OneOnOneChatRoomListResponse;
import com.example.chatting.chat.oneonone.room.controller.dto.response.OneOnOneChatRoomResponse;
import com.example.chatting.member.application.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OneOnOneChatRoomFacade {

    private final MemberService memberService;
    private final OneOnOneChatRoomService oneOnOneChatRoomService;

    public long createOneOnOneChatRoom(long memberId, long otherMemberId){
        long smallerId = memberId < otherMemberId ? memberId : otherMemberId;
        long biggerId = memberId > otherMemberId ? memberId : otherMemberId;

        memberService.validateMember(smallerId);
        memberService.validateMember(biggerId);

        oneOnOneChatRoomService.validateOneOnOneChatRoom(smallerId, biggerId);

        return oneOnOneChatRoomService.createOneOnOneChatRoom(smallerId, biggerId);
    }

    public OneOnOneChatRoomListResponse readOneOnOneChatRooms(long memberId){
        memberService.validateMember(memberId);

        List<OneOnOneChatRoomResponse> responseList = oneOnOneChatRoomService.readOneOnOneChatRooms(memberId).stream()
                .map(room -> {
                    long otherMemberUuid = room.getMemberId1() == memberId
                            ? room.getMemberId2()
                            : room.getMemberId1();
                    return OneOnOneChatRoomResponse.of(room.getRoomId(), otherMemberUuid);
                })
                .collect(Collectors.toList());

        return OneOnOneChatRoomListResponse.of(responseList);
    }
}
