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
@TableName("friend_list")
public class FriendList {

    //好友关系id
    @TableField("ID")
    private int id;

    //用户id
    @TableField("user_id")
    private int userId;

    //好友id
    @TableField("friend_user_id")
    private int friendUserId;

    //关系建立时间
    @TableField("relationship_time")
    private LocalDateTime relationshipTime;

}