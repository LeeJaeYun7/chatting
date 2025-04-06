package com.example.chatting.friend.application;

import com.example.chatting.shared.exceptions.CustomException;
import com.example.chatting.shared.exceptions.CustomExceptionType;
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

    public void createFriend(String memberUuid, String friendUuid){
        Friend friend = Friend.of(memberUuid, friendUuid);
        friendRepository.save(friend);
    }

    public List<Friend> readFriendList(String memberUuid){
        return friendRepository.findByMemberUuid(memberUuid);
    }

    public void validateFriend(String memberUuid, String friendUuid){
        Optional<Friend> friend = friendRepository.findByMemberUuidAndFriendUuid(memberUuid, friendUuid);

        if(friend.isPresent()){
            throw new CustomException(CustomExceptionType.CHATROOM_DUPLICATED);
        }
    }
}
