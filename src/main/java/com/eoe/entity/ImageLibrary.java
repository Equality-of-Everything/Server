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
@TableName("image_library")
public class ImageLibrary {

    @TableField("ID")
    private int id; // ID

    @TableField("share_info_id")
    private int shareInfoId; // 与图文与视频分享信息表关联ID

    @TableField("image_url")
    private String imageUrl; // 图片地址

    @TableField("upload_time")
    private LocalDateTime uploadTime; // 上传时间

    @TableField("friend_share_id")
    private int friendShareId; // 关联friend_share的id
}
