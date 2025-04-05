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

    public String createOneOnOneChatRoom(String uuid1, String uuid2){
        String smallerUuid = uuid1.compareTo(uuid2) < 0 ? uuid1 : uuid2;
        String biggerUuid = uuid1.compareTo(uuid2) > 0 ? uuid1 : uuid2;

        memberService.validateMember(smallerUuid);
        memberService.validateMember(biggerUuid);

        oneOnOneChatRoomService.validateOneOnOneChatRoom(smallerUuid, biggerUuid);

        return oneOnOneChatRoomService.createOneOnOneChatRoom(smallerUuid, biggerUuid);
    }

    public OneOnOneChatRoomListResponse readOneOnOneChatRooms(String uuid){
        memberService.validateMember(uuid);

        List<OneOnOneChatRoom> oneOnOneChatRoomList = oneOnOneChatRoomService.readOneOnOneChatRooms(uuid);
        List<String> oneOnOneChatRoomIds = oneOnOneChatRoomList.stream()
                                                             .map(OneOnOneChatRoom::getRoomUuid)
                                                             .toList();

        return OneOnOneChatRoomListResponse.of(oneOnOneChatRoomIds);
    }
}
