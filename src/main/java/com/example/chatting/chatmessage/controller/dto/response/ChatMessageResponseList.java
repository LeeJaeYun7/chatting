package com.example.chatting.chatmessage.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class ChatMessageResponseList {

    private String roomUuid;
    private List<ChatMessageResponse> messages;

    @Builder
    public ChatMessageResponseList(String roomUuid, List<ChatMessageResponse> messages){
        this.roomUuid = roomUuid;
        this.messages = messages;
    }

    public static ChatMessageResponseList of(String roomUuid, List<ChatMessageResponse> messages){
        return ChatMessageResponseList.builder()
                                      .roomUuid(roomUuid)
                                      .messages(messages)
                                      .build();
    }


}
