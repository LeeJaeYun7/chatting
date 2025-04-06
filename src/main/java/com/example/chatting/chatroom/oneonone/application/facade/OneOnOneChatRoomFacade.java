package com.example.chatting.chatroom.oneonone.application.facade;

import com.example.chatting.chatroom.oneonone.controller.dto.response.OneOnOneChatRoomListResponse;
import com.example.chatting.chatroom.oneonone.controller.dto.response.OneOnOneChatRoomResponse;
import com.example.chatting.chatroom.oneonone.application.OneOnOneChatRoomService;
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

    public String createOneOnOneChatRoom(String memberUuid, String otherMemberUuid){
        String smallerUuid = memberUuid.compareTo(otherMemberUuid) < 0 ? memberUuid : otherMemberUuid;
        String biggerUuid = memberUuid.compareTo(otherMemberUuid) > 0 ? memberUuid : otherMemberUuid;

        memberService.validateMember(smallerUuid);
        memberService.validateMember(biggerUuid);

        oneOnOneChatRoomService.validateOneOnOneChatRoom(smallerUuid, biggerUuid);

        return oneOnOneChatRoomService.createOneOnOneChatRoom(smallerUuid, biggerUuid);
    }

    public OneOnOneChatRoomListResponse readOneOnOneChatRooms(String memberUuid){
        memberService.validateMember(memberUuid);

        List<OneOnOneChatRoomResponse> responseList = oneOnOneChatRoomService.readOneOnOneChatRooms(memberUuid).stream()
                .map(room -> {
                    String otherMemberUuid = room.getMemberUuid1().equals(memberUuid)
                            ? room.getMemberUuid2()
                            : room.getMemberUuid1();
                    return OneOnOneChatRoomResponse.of(room.getRoomUuid(), otherMemberUuid);
                })
                .collect(Collectors.toList());

        return OneOnOneChatRoomListResponse.of(responseList);
    }
}
