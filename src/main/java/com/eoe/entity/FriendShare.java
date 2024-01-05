package com.eoe.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("friend_share")
public class FriendShare {

    // ID
    @TableField("ID")
    private int id;

    // 个人信息表关联ID
    @TableField("user_info_id")
    private int userInfoId;

    // 分享视频地址
    @TableField("video_url")
    private String videoUrl;

    // 图文地址
    @TableField("image_url")
    private String imageUrl;

    // 文字内容
    @TableField("text_content")
    private String textContent;

    // 点赞数
    @TableField("like_count")
    private int likeCount;

    // 与评论表关联ID
    @TableField("comment_id")
    private int commentId;

    // 与点赞人用户ID表关联ID
    @TableField("like_user_id")
    private int likeUserId;

    // 评论时间
    @TableField("comment_time")
    private LocalDateTime commentTime;
    // 关联friend_share的id

    @TableField("image_library_id")
    private int imageLibraryId;

    public void setCommentTime(LocalDateTime localDateTime) {
        this.commentTime = localDateTime;

    }
}