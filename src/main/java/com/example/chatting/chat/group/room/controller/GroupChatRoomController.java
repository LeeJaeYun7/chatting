package com.example.chatting.chat.group.room.controller;

import com.example.chatting.chat.group.room.application.facade.GroupChatRoomFacade;
import com.example.chatting.chat.group.room.controller.dto.GroupChatRoomCreateRequest;
import com.example.chatting.security.auth.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupChatRoomController {

    private final GroupChatRoomFacade groupChatRoomFacade;

    @PostMapping("/api/v1/chatRoom/group")
    public void createGroupChatRoom(@RequestBody GroupChatRoomCreateRequest request){
        long memberId = AuthenticationUtils.getMemberId();
        String name = request.getName();
        List<Long> participantIds = request.getParticipantIds();

        groupChatRoomFacade.createGroupChatRoom(memberId, name, participantIds);
    }

}
