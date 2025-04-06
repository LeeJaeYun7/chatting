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
        String roomUuid = request.getRoomUuid();
        String senderUuid = request.getSenderUuid();
        String receiverUuid = request.getReceiverUuid();
        String content = request.getContent();

        chatMessageFacade.createChatMessage(roomUuid, senderUuid, receiverUuid, content);
    }

    @GetMapping("/api/v1/chatMessage")
    public ResponseEntity<ChatMessageResponseList> readChatMessages(@RequestParam String roomUuid) {
        ChatMessageResponseList responseList = chatMessageFacade.getChatMessagesByRoomUuid(roomUuid);

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
