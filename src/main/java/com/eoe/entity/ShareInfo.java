package com.eoe.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/1 15:55
 * @Decription :
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("share_info")
public class ShareInfo {

    // ID - 唯一标识符
    @TableField("ID")
    private int id;

    // Personal Info Id - 与个人信息表关联的ID
    @TableField("user_info_id")
    private int userInfoId;

    // Map Info Id - 地图背后信息存储ID
    @TableField("map_info_id")
    private int mapInfoId;

    // IP - IP地址
    @TableField("IP")
    private String ip;

    // Video URL - 视频地址
    @TableField("video_url")
    private String videoUrl;

    // Image Library Id - 与图片库-图文与视频分享信息表关联的ID
    @TableField("image_library_id")
    private int imageLibraryId;

    // Text - 文字内容
    @TableField("text")
    private String text;

    // Type - 类型（区分视频和图文）,1代表视频，0代表图文
    @TableField("type")
    private String type;

    // Like Count - 点赞数
    @TableField("like_count")
    private int likeCount;

    // Comment Id - 与评论表关联ID
    @TableField("comment_id")
    private int commentId;

    // Tag Id - 与标签表关联的ID
    @TableField("tag_id")
    private int tagId;

    // Like User Id - 与点赞人用户ID表关联ID
    @TableField("like_user_id")
    private int likeUserId;

    // Upload Time - 上传时间
    @TableField("upload_time")
    private Date uploadTime;

    //vr_image_url - vr地址
    @TableField("vr_image_url")
    private String vrImageUrl;


}
