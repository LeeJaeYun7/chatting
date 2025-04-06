package com.example.chatting.friend.application.facade;

import com.example.chatting.friend.controller.dto.response.FriendResponse;
import com.example.chatting.friend.domain.Friend;
import com.example.chatting.friend.application.FriendService;
import com.example.chatting.member.application.MemberService;
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
    public void createFriend(String memberUuid, String friendUuid){
        memberService.validateMember(friendUuid);
        friendService.validateFriend(memberUuid, friendUuid);

        friendService.createFriend(memberUuid, friendUuid);
    }

    public List<FriendResponse> readFriendList(String memberUuid){
        return friendService.readFriendList(memberUuid)
                            .stream()
                            .map(this::createFriendResponse)
                            .sorted((friend1, friend2) -> friend1.getName().compareTo(friend2.getName()))
                            .collect(Collectors.toList());
    }

    public FriendResponse createFriendResponse(Friend friend){
        String friendUuid = friend.getFriendUuid();
        String name = memberService.getMemberNameByMemberUuid(friendUuid);

        return FriendResponse.of(friendUuid, name);
    }
}
