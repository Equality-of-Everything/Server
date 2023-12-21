package com.eoe.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @TableField("ID")
    private int id; // ID

    @TableField("share_info_id")
    private int shareInfoId; // 与图文与视频分享信息表关联ID

    @TableField("type")
    private String type; // 类型（区别评论来自动态还是视频）

    @TableField("friend_share_id")
    private int friendShareId; // 与好友动态分享关联ID

    @TableField("user_id")
    private int userId; // 评论用户ID

    @TableField("comment_time")
    private LocalDateTime commentTime; // 评论时间

    @TableField("video_id")
    private int videoId; // 视频ID

    @TableField("comment_text")
    private String commentText; // 评论内容

    @TableField("comment_date")
    private LocalDateTime commentDate; // 评论时间

}
