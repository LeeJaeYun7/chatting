package com.example.chatting.chat.group.message.controller;

import com.example.chatting.chat.group.message.application.facade.GroupChatMessageFacade;
import com.example.chatting.chat.group.message.controller.dto.GroupChatMessageCreateRequest;
import com.example.chatting.chat.group.message.controller.dto.GroupChatMessageReadRequest;
import com.example.chatting.security.auth.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupChatMessageController {

    private final GroupChatMessageFacade groupChatMessageFacade;

    @PostMapping("/api/v1/chatMessage/group")
    public void createGroupChatMessage(@RequestBody GroupChatMessageCreateRequest request) {
        long roomId = Long.parseLong(request.getRoomId());
        long senderId = Long.parseLong(request.getSenderId());
        String content = request.getContent();

        groupChatMessageFacade.createGroupChatMessage(roomId, senderId, content);
    }

    @PostMapping("/api/v1/chatMessage/group/list")
    public void readGroupChatMessages(@RequestBody GroupChatMessageReadRequest request) {
        long roomId = request.getRoomId();

        groupChatMessageFacade.readGroupChatMessages(roomId);
    }
}
