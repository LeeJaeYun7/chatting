package com.example.chatting.chatroom.oneonone.service;

import com.example.chatting.chatroom.oneonone.domain.OneOnOneChatRoom;
import com.example.chatting.chatroom.oneonone.infrastructure.OneOnOneChatRoomRepository;
import com.example.chatting.commons.exceptions.CustomException;
import com.example.chatting.commons.exceptions.CustomExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OneOnOneChatRoomService {

    private final OneOnOneChatRoomRepository oneOnOneChatRoomRepository;

    public long createOneOnOneChatRoom(long memberId1, long memberId2){
        OneOnOneChatRoom oneOnOneChatRoom = OneOnOneChatRoom.of(memberId1, memberId2);
        OneOnOneChatRoom savedOneOnOneChatRoom = oneOnOneChatRoomRepository.save(oneOnOneChatRoom);
        return savedOneOnOneChatRoom.getId();
    }

    public List<OneOnOneChatRoom> readOneOnOneChatRooms(long memberId){
        return oneOnOneChatRoomRepository.findByMemberId(memberId);
    }

    public void validateOneOnOneChatRoom(long memberId1, long memberId2){
        Optional<OneOnOneChatRoom> oneOnOneChatRoom = oneOnOneChatRoomRepository.findByMembers(memberId1, memberId2);

        if(oneOnOneChatRoom.isPresent()){
            throw new CustomException(CustomExceptionType.CHATROOM_DUPLICATED);
        }
    }



}
