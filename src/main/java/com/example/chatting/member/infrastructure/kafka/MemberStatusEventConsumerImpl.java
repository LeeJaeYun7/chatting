package com.example.chatting.member.infrastructure.kafka;

import com.example.chatting.member.application.facade.MemberFacade;
import com.example.chatting.member.domain.event.MemberStatusEventConsumer;
import com.example.chatting.member.domain.event.MemberStatusEvent;
import com.example.chatting.member.domain.event.enums.MemberStatusConst;
import com.example.chatting.shared.utils.JsonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberStatusEventConsumerImpl implements MemberStatusEventConsumer {

    private final JsonConverter jsonConverter;
    private final MemberFacade memberFacade;

    @KafkaListener(
            topics = MemberStatusConst.MEMBER_STATUS_TOPIC,
            groupId = "member-status-group"
    )
    public void receiveMemberStatus(String message) {
        MemberStatusEvent event = jsonConverter.convertFromJson(message, MemberStatusEvent.class);

        long memberId = Long.parseLong(event.getMemberId());
        boolean isOnline = event.isOnline();

        memberFacade.changeMemberStatus(memberId, isOnline);
    }
}
