package com.example.chatting.chatroom.oneonone.controller;

import com.example.chatting.chatroom.oneonone.controller.dto.request.OneOnOneChatRoomCreateRequest;
import com.example.chatting.chatroom.oneonone.controller.dto.response.OneOnOneChatRoomCreateResponse;
import com.example.chatting.chatroom.oneonone.controller.dto.response.OneOnOneChatRoomListResponse;
import com.example.chatting.chatroom.oneonone.application.facade.OneOnOneChatRoomFacade;
import com.example.chatting.security.auth.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OneOnOneChatRoomController {

    private final OneOnOneChatRoomFacade oneOnOneChatRoomFacade;

    @PostMapping("/api/v1/chatRoom/oneOnOne")
    public ResponseEntity<OneOnOneChatRoomCreateResponse> createOneOnOneChatRoom(@RequestBody OneOnOneChatRoomCreateRequest request){
        long memberId = AuthenticationUtils.getMemberId();
        long otherMemberId = request.getOtherMemberId();

        long roomId = oneOnOneChatRoomFacade.createOneOnOneChatRoom(memberId, otherMemberId);
        OneOnOneChatRoomCreateResponse response = OneOnOneChatRoomCreateResponse.of(roomId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/v1/chatRoom/oneOnOne/list")
    public ResponseEntity<OneOnOneChatRoomListResponse> readOneOnOneChatRooms(){
        long memberId = AuthenticationUtils.getMemberId();

        OneOnOneChatRoomListResponse response = oneOnOneChatRoomFacade.readOneOnOneChatRooms(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
