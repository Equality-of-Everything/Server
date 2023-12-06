package com.eoe.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("instant_messaging")
public class InstantMessaging {

    // 消息id
    @TableField("ID")
    private int id;

    // 发送者id
    @TableField("sender_user_id")
    private int senderUserId;

    // 接收者id
    @TableField("receiver_user_id")
    private int receiverUserId;

    // 内容
    @TableField("content")
    private String content;

    // 时间
    @TableField("time")
    private LocalDateTime time;

}