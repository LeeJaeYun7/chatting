package com.example.chatting.chat.oneonone.message.controller;

import com.example.chatting.chat.oneonone.message.application.facade.ChatMessageFacade;
import com.example.chatting.chat.oneonone.message.controller.dto.request.ChatMessageRequest;
import com.example.chatting.chat.oneonone.message.controller.dto.response.ChatMessageResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageFacade chatMessageFacade;

    @PostMapping("/api/v1/chatMessage/oneOnOne")
    public void createChatMessage(@RequestBody ChatMessageRequest request){
        long roomId = Long.parseLong(request.getRoomId());
        long senderId = Long.parseLong(request.getSenderId());
        String content = request.getContent();

        chatMessageFacade.createChatMessage(roomId, senderId, content);
    }

    @GetMapping("/api/v1/chatMessage/oneOnOne")
    public ResponseEntity<ChatMessageResponseList> readChatMessages(@RequestParam long roomId) {
        ChatMessageResponseList responseList = chatMessageFacade.getChatMessagesByRoomId(roomId);

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
