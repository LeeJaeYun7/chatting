package com.example.chatting.friend.service;

import com.example.chatting.commons.exceptions.CustomException;
import com.example.chatting.commons.exceptions.CustomExceptionType;
import com.example.chatting.friend.domain.Friend;
import com.example.chatting.friend.infrastructure.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public void createFriend(String uuid, String friendUuid){
        Friend friend = Friend.of(uuid, friendUuid);
        friendRepository.save(friend);
    }

    public List<Friend> readFriendList(String uuid){
        return friendRepository.findByUuid(uuid);
    }

    public void validateFriend(String uuid, String friendUuid){
        Optional<Friend> friend = friendRepository.findByUuidAndFriendUuid(uuid, friendUuid);

        if(friend.isPresent()){
            throw new CustomException(CustomExceptionType.CHATROOM_DUPLICATED);
        }
    }
}
