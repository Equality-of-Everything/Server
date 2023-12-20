package com.eoe.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("likes")
public class Likes {

    @TableField("ID")
    private int id; // 点赞记录的唯一标识符

    @TableField("video_id")
    private int videoId; // 被点赞的视频的唯一标识符

    @TableField("user_id")
    private int userId; // 点赞用户的唯一标识符

    @TableField("timestamp")
    private Timestamp timestamp; // 点赞操作的时间戳

    @TableField("is_liked")
    private boolean isLiked; // 表示是否点赞的标志

    public Likes(int userId, int videoId, boolean b) {
        this.userId = userId;
        this.videoId = videoId;
        this.isLiked = b;
    }

    public Likes(int userId, int videoId, boolean b, Timestamp timestamp) {
        this.userId = userId;
        this.videoId = videoId;
        this.isLiked = b;
        this.timestamp = timestamp;
    }

    public void setTimestamp(java.sql.Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
