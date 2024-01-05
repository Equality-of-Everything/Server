package com.eoe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author : Zhang
 * @Date : Created in 2024/1/2 20:34
 * @Decription : 朋友圈子项
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendCircleItem {
    // 发布者的用户名
    private String userName;
    // 发布者的头像
    private String avatarUrl;
    // 发布者的文字内容
    private String textContent;
    // 包含图片URL列表
    private List<String> mediaUrls;
    // 发布时间
    private String publishTime;
    //视频url
    private String videoUrl;
}
