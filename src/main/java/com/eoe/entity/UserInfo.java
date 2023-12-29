package com.eoe.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("user_info")
public class UserInfo {
    @TableId(type = IdType.ASSIGN_UUID)
    private int userId; // 用户ID

    @TableField("username")
    private String username; // 用户名

    @TableField("nickname")
    private String nickname; // 昵称

    @TableField("avatar")
    private String avatar; // 头像

    @TableField("gender")
    private String gender; // 性别

    @TableField("status")
    private String status; // 状态

    @TableField("signature")
    private String signature; // 个性签名

    @TableField("birthday")
    private String birthday; // 生日

    @TableField("last_modified_time")
    private String lastModifiedTime; // 最近修改时间

    @TableField("address")
    private String address; // 地址

    @TableField("share_info_id")
    private int shareInfoId; // 与图文与视频分享信息表关联的ID

    @TableField("trajectory_id")
    private int trajectoryId; // 与轨迹表关联的ID

    @TableField("friend_share_id")
    private int friendShareId; // 与好友动态分享关联的ID

    // 邮箱
    @TableField("email")
    private String email;

    public UserInfo(int userId) {
        this.userId = userId;
    }

    public UserInfo(int userId, String username, String avatar) {
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
    }

    public UserInfo(int userId, String username, String avatar, int shareInfoId,String email) {
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
        this.shareInfoId = shareInfoId;
        this.email = email;
    }
}