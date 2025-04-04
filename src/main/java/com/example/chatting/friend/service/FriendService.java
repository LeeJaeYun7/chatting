package com.example.chatting.friend.service;

import com.example.chatting.friend.domain.Friend;
import com.example.chatting.friend.infrastructure.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;

    public void createFriend(long memberId, long friendMemberId){
        Friend friend = Friend.of(memberId, friendMemberId);
        friendRepository.save(friend);
    }

    public List<Friend> getFriendList(long memberId){
        return friendRepository.findByMemberId(memberId);
    }
}
