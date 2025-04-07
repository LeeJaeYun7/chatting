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

    public void createFriend(long memberId, long friendId){
        memberService.validateMember(friendId);
        friendService.validateFriend(memberId, friendId);

        friendService.createFriend(memberId, friendId);
    }

    public List<FriendResponse> readFriendList(long memberId){
        return friendService.readFriendList(memberId)
                            .stream()
                            .map(this::createFriendResponse)
                            .sorted((friend1, friend2) -> friend1.getName().compareTo(friend2.getName()))
                            .collect(Collectors.toList());
    }

    public FriendResponse createFriendResponse(Friend friend){
        long friendId = friend.getFriendId();
        String name = memberService.getMemberNameByMemberId(friendId);

        return FriendResponse.of(friendId, name);
    }
}
