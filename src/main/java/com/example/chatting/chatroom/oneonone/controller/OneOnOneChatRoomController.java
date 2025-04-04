package com.example.chatting.chatroom.oneonone.controller;

import com.example.chatting.chatroom.oneonone.controller.dto.request.OneOnOneChatRoomCreateRequest;
import com.example.chatting.chatroom.oneonone.controller.dto.response.OneOnOneChatRoomCreateResponse;
import com.example.chatting.chatroom.oneonone.service.facade.OneOnOneChatRoomFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OneOnOneChatRoomController {

    private final OneOnOneChatRoomFacade oneOnOneChatRoomFacade;

    @PostMapping("/api/v1/chatRoom/oneOnOne/create")
    public ResponseEntity<OneOnOneChatRoomCreateResponse> createOneOnOneChatRoom(@RequestBody OneOnOneChatRoomCreateRequest request){
        long memberId1 = request.getMemberId1();
        long memberId2 = request.getMemberId2();

        long oneOnOneChatRoomId = oneOnOneChatRoomFacade.createOneOnOneChatRoom(memberId1, memberId2);
        OneOnOneChatRoomCreateResponse response = OneOnOneChatRoomCreateResponse.of(oneOnOneChatRoomId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
