package com.example.chatting.friend.controller;

import com.example.chatting.friend.controller.dto.request.FriendCreateRequest;
import com.example.chatting.friend.controller.dto.response.FriendResponse;
import com.example.chatting.friend.application.facade.FriendFacade;
import com.example.chatting.security.auth.AuthenticationUtils;
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
        long memberId = AuthenticationUtils.getMemberId();
        long friendId = friendCreateRequest.getFriendId();

        friendFacade.createFriend(memberId, friendId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/api/v1/friend/list")
    public ResponseEntity<List<FriendResponse>> readFriendList(){
        long memberId = AuthenticationUtils.getMemberId();

        List<FriendResponse> friendResponseList = friendFacade.readFriendList(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(friendResponseList);
    }
}
