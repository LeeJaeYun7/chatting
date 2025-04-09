package com.example.chatting.member.infrastructure.kafka;

import com.example.chatting.member.application.facade.MemberFacade;
import com.example.chatting.member.domain.event.MemberStatusChangeEvent;
import com.example.chatting.member.domain.event.MemberStatusChangeEventConsumer;
import com.example.chatting.member.infrastructure.kafka.enums.MemberStatusTopics;
import com.example.chatting.shared.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberStatusChangeEventConsumerImpl implements MemberStatusChangeEventConsumer {

    private final JsonConverter jsonConverter;
    private final MemberFacade memberFacade;

    @KafkaListener(
            topics = MemberStatusTopics.MEMBER_STATUS_TOPIC,
            groupId = "member-status-group"
    )
    public void receiveMemberStatus(String message) {
        MemberStatusChangeEvent event = jsonConverter.convertFromJson(message, MemberStatusChangeEvent.class);

        long memberId = Long.parseLong(event.getMemberId());
        boolean isOnline = event.isOnline();

        memberFacade.notifyMemberStatus(memberId, isOnline);
    }
}
