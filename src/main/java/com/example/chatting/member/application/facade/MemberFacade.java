package com.example.chatting.member.application.facade;

import com.example.chatting.friend.application.FriendService;
import com.example.chatting.member.application.MemberService;
import com.example.chatting.member.domain.event.MemberStatusNotificationEvent;
import com.example.chatting.member.domain.event.MemberStatusNotificationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberFacade {

    private final FriendService friendService;
    private final MemberService memberService;
    private final MemberStatusNotificationEventPublisher memberStatusNotificationEventPublisher;

    public void notifyMemberStatus(long memberId, boolean isOnline){
        memberService.validateMember(memberId);
        memberService.updateMemberStatus(memberId, isOnline);

        List<String> onlineFriendIds = friendService.readFriendList(memberId)
                                                    .stream()
                                                    .filter(friend -> memberService.getMemberStatus(friend.getFriendId()))  // 친구의 상태가 온라인인 친구들만 필터링
                                                    .map(friend -> String.valueOf(friend.getFriendId()))  // 친구 ID를 String으로 변환
                                                    .toList();

        MemberStatusNotificationEvent event = MemberStatusNotificationEvent.of(String.valueOf(memberId), onlineFriendIds, isOnline);
        memberStatusNotificationEventPublisher.publishMemberStatusNotificationEvent(event);
    }
}
