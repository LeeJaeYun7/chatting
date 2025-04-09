package com.example.chatting.chat.group.message.controller;

import com.example.chatting.chat.group.message.application.facade.GroupChatMessageFacade;
import com.example.chatting.chat.group.message.controller.dto.GroupChatMessageCreateRequest;
import com.example.chatting.chat.group.message.controller.dto.GroupChatMessageReadRequest;
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

        System.out.println("roomId 도착");
        System.out.println("senderId 도착");
        System.out.println("content 도착");

        System.out.println(roomId);
        System.out.println(senderId);
        System.out.println(content);

        groupChatMessageFacade.createGroupChatMessage(roomId, senderId, content);
    }

    @PostMapping("/api/v1/chatMessage/group/list")
    public void readGroupChatMessages(@RequestBody GroupChatMessageReadRequest request) {
        long roomId = request.getRoomId();

        groupChatMessageFacade.readGroupChatMessages(roomId);
    }
}
