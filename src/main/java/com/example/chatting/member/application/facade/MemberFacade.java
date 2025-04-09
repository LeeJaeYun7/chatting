package com.example.chatting.member.application.facade;

import com.example.chatting.friend.application.FriendService;
import com.example.chatting.member.application.MemberService;
import com.example.chatting.member.domain.event.MemberStatusToFriendEvent;
import com.example.chatting.member.domain.event.MemberStatusToFriendEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberFacade {

    private final FriendService friendService;
    private final MemberService memberService;
    private final MemberStatusToFriendEventProducer memberStatusToFriendEventProducer;

    public void changeMemberStatus(long memberId, boolean isOnline){
        memberService.validateMember(memberId);
        memberService.updateMemberStatus(memberId, isOnline);

        List<String> friendIds = friendService.readFriendList(memberId)
                                              .stream()
                                              .map(friend -> String.valueOf(friend.getFriendId()))  // Long -> String으로 변환
                                              .toList();

        MemberStatusToFriendEvent event = MemberStatusToFriendEvent.of(friendIds, isOnline);
        memberStatusToFriendEventProducer.sendMemberStatusToFriendEvent(event);
    }
}
