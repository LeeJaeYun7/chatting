package com.example.chatting.chat.oneonone.message.application.facade;

import com.example.chatting.chat.oneonone.message.application.OneOnOneChatMessageService;
import com.example.chatting.chat.oneonone.message.controller.dto.response.OneOnOneChatMessageResponse;
import com.example.chatting.chat.oneonone.message.controller.dto.response.OneOnOneChatMessageResponseList;
import com.example.chatting.chat.oneonone.message.domain.OneOnOneChatMessage;
import com.example.chatting.chat.oneonone.message.domain.event.OneOnOneChatMessageEvent;
import com.example.chatting.chat.oneonone.message.domain.event.OneOnOneChatMessageEventPublisher;
import com.example.chatting.chat.oneonone.room.application.OneOnOneChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OneOnOneChatMessageFacade {

    private final OneOnOneChatRoomService oneOnOneChatRoomService;
    private final OneOnOneChatMessageService oneOnOneChatMessageService;
    private final OneOnOneChatMessageEventPublisher oneOnOneChatMessageEventPublisher;

    public void createOneOnOneChatMessage(long roomId, long senderId, String content){
        long receiverId = oneOnOneChatRoomService.getChatRoomReceiverId(roomId, senderId);
        LocalDateTime timestamp = oneOnOneChatMessageService.createChatMessage(roomId, senderId, receiverId, content);

        OneOnOneChatMessageEvent oneOnOneChatMessageEvent = OneOnOneChatMessageEvent.of(String.valueOf(roomId), String.valueOf(senderId), String.valueOf(receiverId), content, timestamp);
        oneOnOneChatMessageEventPublisher.publishOneOnOneChatMessageEvent(oneOnOneChatMessageEvent);
    }

    public OneOnOneChatMessageResponseList getOneOnOneChatMessagesByRoomId(long roomId) {
        List<OneOnOneChatMessageResponse> messages = oneOnOneChatMessageService.getChatMessagesByRoomId(roomId).stream()
                                                                    .sorted(Comparator.comparing(OneOnOneChatMessage::getTimestamp))
                                                                    .map(oneOnOneChatMessage -> OneOnOneChatMessageResponse.of(
                                                                            oneOnOneChatMessage.getSenderId(),
                                                                            oneOnOneChatMessage.getReceiverId(),
                                                                            oneOnOneChatMessage.getContent(),
                                                                            oneOnOneChatMessage.getTimestamp()))
                                                                   .toList();

        return OneOnOneChatMessageResponseList.of(roomId, messages);
    }
}
