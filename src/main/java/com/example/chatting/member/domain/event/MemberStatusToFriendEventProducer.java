package com.example.chatting.member.domain.event;

public interface MemberStatusToFriendEventProducer {

    void sendMemberStatusToFriendEvent(MemberStatusToFriendEvent event);
}
