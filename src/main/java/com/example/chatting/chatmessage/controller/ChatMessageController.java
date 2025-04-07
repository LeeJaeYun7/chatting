package com.example.chatting.chatmessage.controller;

import com.example.chatting.chatmessage.controller.dto.request.ChatMessageRequest;
import com.example.chatting.chatmessage.controller.dto.response.ChatMessageResponseList;
import com.example.chatting.chatmessage.application.facade.ChatMessageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageFacade chatMessageFacade;

    @PostMapping("/api/v1/chatMessage")
    public void createChatMessage(@RequestBody ChatMessageRequest request){
        long roomId = Long.parseLong(request.getRoomId());
        long senderId = Long.parseLong(request.getSenderId());
        long receiverId = Long.parseLong(request.getReceiverId());
        String content = request.getContent();

        chatMessageFacade.createChatMessage(roomId, senderId, receiverId, content);
    }

    @GetMapping("/api/v1/chatMessage")
    public ResponseEntity<ChatMessageResponseList> readChatMessages(@RequestParam long roomId) {
        ChatMessageResponseList responseList = chatMessageFacade.getChatMessagesByRoomId(roomId);

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
