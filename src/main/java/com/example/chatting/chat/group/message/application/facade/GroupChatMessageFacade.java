package com.example.chatting.chat.group.message.application.facade;

import com.example.chatting.chat.group.message.application.GroupChatMessageService;
import com.example.chatting.chat.group.message.domain.GroupChatMessage;
import com.example.chatting.chat.group.message.domain.event.GroupChatMessageEvent;
import com.example.chatting.chat.group.message.domain.event.GroupChatMessageEventProducer;
import com.example.chatting.chat.group.participant.application.GroupChatParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GroupChatMessageFacade {

    private final GroupChatMessageService groupChatMessageService;
    private final GroupChatParticipantService groupChatParticipantService;
    private final GroupChatMessageEventProducer groupChatMessageEventProducer;

    public void createGroupChatMessage(long roomId, long senderId, String content){
        LocalDateTime timestamp = groupChatMessageService.createGroupChatMessage(roomId, senderId, content);

        List<String> participantIds = groupChatParticipantService.getGroupChatParticipants(roomId)
                                                                 .stream()  // 스트림으로 변환
                                                                 .map(String::valueOf)  // 각 Long 값을 String으로 변환
                                                                 .collect(Collectors.toList());

        GroupChatMessageEvent groupChatMessageEvent = GroupChatMessageEvent.of(String.valueOf(roomId), String.valueOf(senderId), participantIds, content, timestamp);
        groupChatMessageEventProducer.sendGroupChatMessageEvent(groupChatMessageEvent);
    }

    public List<GroupChatMessage> readGroupChatMessages(long roomId) {
        return groupChatMessageService.getGroupChatMessages(roomId);
    }
}
