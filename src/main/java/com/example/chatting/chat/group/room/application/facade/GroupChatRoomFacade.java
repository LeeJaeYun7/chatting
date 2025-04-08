package com.example.chatting.chat.group.room.application.facade;

import com.example.chatting.chat.group.participant.application.GroupChatParticipantService;
import com.example.chatting.chat.group.room.application.GroupChatRoomService;
import com.example.chatting.member.application.MemberService;
import com.example.chatting.shared.exceptions.CustomException;
import com.example.chatting.shared.exceptions.CustomExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupChatRoomFacade {
    private static final long MAX_PARTICIPANT_COUNT = 100;

    private final MemberService memberService;
    private final GroupChatRoomService groupChatRoomService;
    private final GroupChatParticipantService groupChatParticipantService;

    @Transactional
    public void createGroupChatRoom(long creatorId, String name, List<Long> participantIds){
        participantIds.forEach(memberService::validateMember); // 스트림 방식

        long totalCount = participantIds.size() + 1; // 채팅방 생성자 포함

        if (totalCount > MAX_PARTICIPANT_COUNT) {
            throw new CustomException(CustomExceptionType.GROUP_CHATROOM_PARTICIPANT_LIMIT_EXCEEDED);
        }

        long roomId = groupChatRoomService.createGroupChatRoom(creatorId, name);
        groupChatParticipantService.createGroupChatParticipants(roomId, creatorId, participantIds);
    }
}
