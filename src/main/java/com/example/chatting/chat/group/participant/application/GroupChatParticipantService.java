package com.example.chatting.chat.group.participant.application;

import com.example.chatting.chat.group.participant.domain.GroupChatParticipant;
import com.example.chatting.chat.group.participant.infrastructure.GroupChatParticipantRepository;
import com.example.chatting.chat.group.room.domain.enums.ParticipantRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupChatParticipantService {

    private final GroupChatParticipantRepository groupChatParticipantRepository;

    public void createGroupChatParticipants(long roomId, long creatorId, List<Long> participantIds){
        // 방장(creatorId) 생성
        GroupChatParticipant creator = new GroupChatParticipant(roomId, creatorId, ParticipantRole.ADMIN);

        // 나머지 참가자는 기본적으로 MEMBER
        List<GroupChatParticipant> participants = participantIds.stream()
                                                                .map(memberId -> new GroupChatParticipant(roomId, memberId, ParticipantRole.MEMBER))
                                                                .collect(Collectors.toList());

        // 방장도 참가자 목록에 추가
        participants.add(creator);

        // DB에 저장
        groupChatParticipantRepository.saveAll(participants);
    }

    // 주어진 roomId에 속한 모든 참여자들의 ID를 반환
    public List<Long> getGroupChatParticipants(long roomId) {
        // 해당 roomId에 속한 모든 참여자 찾기
        List<GroupChatParticipant> participants = groupChatParticipantRepository.findByRoomId(roomId);

        // 참여자 목록에서 ID만 추출하여 반환
        return participants.stream()
                           .map(GroupChatParticipant::getMemberId)
                           .collect(Collectors.toList());
    }
}
