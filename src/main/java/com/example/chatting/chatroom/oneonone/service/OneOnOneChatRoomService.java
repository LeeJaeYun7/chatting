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

    public String createOneOnOneChatRoom(String uuid1, String uuid2){
        OneOnOneChatRoom oneOnOneChatRoom = OneOnOneChatRoom.of(uuid1, uuid2);
        OneOnOneChatRoom savedOneOnOneChatRoom = oneOnOneChatRoomRepository.save(oneOnOneChatRoom);
        return savedOneOnOneChatRoom.getRoomUuid();
    }

    public List<OneOnOneChatRoom> readOneOnOneChatRooms(String uuid){
        return oneOnOneChatRoomRepository.findByUuid(uuid);
    }

    public void validateOneOnOneChatRoom(String uuid1, String uuid2){
        Optional<OneOnOneChatRoom> oneOnOneChatRoom = oneOnOneChatRoomRepository.findByUuids(uuid1, uuid2);

        if(oneOnOneChatRoom.isPresent()){
            throw new CustomException(CustomExceptionType.CHATROOM_DUPLICATED);
        }
    }
}
