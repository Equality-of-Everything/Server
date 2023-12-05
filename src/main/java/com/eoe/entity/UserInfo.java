package com.eoe.entity;
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
    private int userId; // 用户ID
    private String username; // 用户名
    private String nickname; // 昵称
    private String avatar; // 头像
    private String gender; // 性别
    private String status; // 状态
    private String signature; // 个性签名
    private LocalDate birthday; // 生日
    private LocalDateTime lastModifiedTime; // 最近修改时间
    private String address; // 地址
    private int shareInfoId; // 与图文与视频分享信息表关联的ID
    private int trajectoryId; // 与轨迹表关联的ID
    private int friendShareId; // 与好友动态分享关联的ID

    public UserInfo(int maxuserId) {
        this.userId = maxuserId + 1;
    }
}