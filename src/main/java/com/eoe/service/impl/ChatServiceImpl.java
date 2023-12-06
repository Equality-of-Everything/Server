package com.eoe.service.impl;

import com.eoe.entity.FriendList;
import com.eoe.entity.InstantMessaging;
import com.eoe.mapper.FriendListMapper;
import com.eoe.mapper.InstantMessagingMapper;
import com.eoe.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private InstantMessagingMapper instantMessagingMapper;

    @Autowired
    private FriendListMapper friendListMapper;

    /**
     * 发送消息
     * @param senderUserId
     * @param receiverUserId
     * @param content
     */
    public void sendMessage(int senderUserId, int receiverUserId, String content) {
        InstantMessaging instantMessaging = new InstantMessaging();
        instantMessaging.setSenderUserId(senderUserId);
        instantMessaging.setReceiverUserId(receiverUserId);
        instantMessaging.setContent(content);
        instantMessaging.setTime(LocalDateTime.now());

        instantMessagingMapper.insertInstantMessaging(instantMessaging);
    }

    /**
     * 添加好友
     * @param userId
     * @param friendUserId
     */
    public void addFriend(int userId, int friendUserId) {
        FriendList friendList = new FriendList();
        friendList.setUserId(userId);
        friendList.setFriendUserId(friendUserId);
        friendList.setRelationshipTime(LocalDateTime.now());

        friendListMapper.insertFriendList(friendList);
    }
}
