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
    public void createFriend(long memberId, long friendMemberId){
        memberService.validateMember(friendMemberId);
        friendService.createFriend(memberId, friendMemberId);
    }

    public List<FriendResponse> getFriendList(long memberId){
        return friendService.getFriendList(memberId)
                            .stream()
                            .map(this::createFriendResponse)
                            .collect(Collectors.toList());
    }

    public FriendResponse createFriendResponse(Friend friend){
        long friendMemberId = friend.getFriendMemberId();
        String name = memberService.getMemberNameById(friendMemberId);

        return FriendResponse.of(friendMemberId, name);
    }
}
