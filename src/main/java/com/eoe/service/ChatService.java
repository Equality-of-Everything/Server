package com.eoe.service;

public interface ChatService {

    /**
     * 发信息
     * @param senderUserId
     * @param receiverUserId
     * @param content
     */
    public void sendMessage(int senderUserId,int receiverUserId,String content);


    /**
     * 添加好友
     * @param userId
     * @param friendUserId
     */
    public void addFriend(int userId,int friendUserId);

}
