package com.example.chatting.chat.oneonone.message.controller;

import com.example.chatting.chat.oneonone.message.application.facade.OneOnOneChatMessageFacade;
import com.example.chatting.chat.oneonone.message.controller.dto.request.OneOnOneChatMessageRequest;
import com.example.chatting.chat.oneonone.message.controller.dto.response.OneOnOneChatMessageResponseList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OneOnOneChatMessageController {

    private final OneOnOneChatMessageFacade oneOnOneChatMessageFacade;

    @GetMapping("/api/v1/chatMessage/oneOnOne")
    public ResponseEntity<OneOnOneChatMessageResponseList> readOneOnOneChatMessages(@RequestParam long roomId) {
        OneOnOneChatMessageResponseList responseList = oneOnOneChatMessageFacade.getOneOnOneChatMessagesByRoomId(roomId);

        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }
}
