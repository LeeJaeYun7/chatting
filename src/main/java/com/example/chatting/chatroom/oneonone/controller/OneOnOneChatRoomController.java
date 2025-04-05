package com.example.chatting.chatroom.oneonone.controller;

import com.example.chatting.chatroom.oneonone.controller.dto.request.OneOnOneChatRoomCreateRequest;
import com.example.chatting.chatroom.oneonone.controller.dto.request.OneOnOneChatRoomListRequest;
import com.example.chatting.chatroom.oneonone.controller.dto.response.OneOnOneChatRoomCreateResponse;
import com.example.chatting.chatroom.oneonone.controller.dto.response.OneOnOneChatRoomListResponse;
import com.example.chatting.chatroom.oneonone.service.facade.OneOnOneChatRoomFacade;
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
        String uuid1 = request.getUuid1();
        String uuid2 = request.getUuid2();

        String oneOnOneChatRoomUuid = oneOnOneChatRoomFacade.createOneOnOneChatRoom(uuid1, uuid2);
        OneOnOneChatRoomCreateResponse response = OneOnOneChatRoomCreateResponse.of(oneOnOneChatRoomUuid);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/api/v1/chatRoom/oneOnOne/list")
    public ResponseEntity<OneOnOneChatRoomListResponse> readOneOnOneChatRooms(@RequestBody OneOnOneChatRoomListRequest request){
        String uuid = request.getUuid();
        OneOnOneChatRoomListResponse response = oneOnOneChatRoomFacade.readOneOnOneChatRooms(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
