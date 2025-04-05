package com.example.chatting.friend.controller;

import com.example.chatting.friend.controller.dto.request.FriendCreateRequest;
import com.example.chatting.friend.controller.dto.request.FriendListRequest;
import com.example.chatting.friend.controller.dto.response.FriendResponse;
import com.example.chatting.friend.service.facade.FriendFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendFacade friendFacade;

    @PostMapping("/api/v1/friend/create")
    public ResponseEntity<Void> createFriend(@RequestBody FriendCreateRequest friendCreateRequest){
        String uuid = friendCreateRequest.getUuid();
        String friendUuid = friendCreateRequest.getFriendUuid();

        friendFacade.createFriend(uuid, friendUuid);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/api/v1/friend/list")
    public ResponseEntity<List<FriendResponse>> readFriendList(@RequestBody FriendListRequest request){
        String uuid = request.getUuid();
        List<FriendResponse> friendResponseList = friendFacade.readFriendList(uuid);
        return ResponseEntity.status(HttpStatus.OK).body(friendResponseList);
    }
}
