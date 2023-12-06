package com.eoe.controller;

import com.eoe.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@Api(tags = "好友相关接口")
public class ChatController {

    @Autowired
    private ChatService chatService;


    @ApiOperation("发信息")
    @PostMapping("/send-message")
    public void sendMessage(@RequestParam("senderUserId") int senderUserId,
                            @RequestParam("receiverUserId") int receiverUserId,
                            @RequestParam("content") String content) {
        chatService.sendMessage(senderUserId, receiverUserId, content);
    }

    @ApiOperation("添加好友")
    @PostMapping("/add-friend")
    public void addFriend(@RequestParam("userId") int userId,
                          @RequestParam("friendUserId") int friendUserId) {
        chatService.addFriend(userId, friendUserId);
    }

    // 其他方法...
}
