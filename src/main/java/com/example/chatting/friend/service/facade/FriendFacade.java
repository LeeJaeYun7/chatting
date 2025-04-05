package com.example.chatting.friend.service.facade;

import com.example.chatting.friend.controller.dto.response.FriendResponse;
import com.example.chatting.friend.domain.Friend;
import com.example.chatting.friend.service.FriendService;
import com.example.chatting.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FriendFacade {

    private final MemberService memberService;
    private final FriendService friendService;

    @Transactional
    public void createFriend(String uuid, String friendUuid){
        memberService.validateMember(friendUuid);
        friendService.validateFriend(uuid, friendUuid);

        friendService.createFriend(uuid, friendUuid);
    }

    public List<FriendResponse> readFriendList(String uuid){
        return friendService.readFriendList(uuid)
                            .stream()
                            .map(this::createFriendResponse)
                            .sorted((friend1, friend2) -> friend1.getName().compareTo(friend2.getName()))
                            .collect(Collectors.toList());
    }

    public FriendResponse createFriendResponse(Friend friend){
        String friendUuid = friend.getFriendUuid();
        String name = memberService.getMemberNameByUuid(friendUuid);

        return FriendResponse.of(friendUuid, name);
    }
}
