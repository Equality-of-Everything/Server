package com.eoe.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_login")
public class UserLogin implements Serializable {

    // 用户ID
    @TableField("user_id")
    private Integer userId;

    // 用户名
    @TableField("username")
    private String username;

    // 密码
    @TableField ("password")
    private String password;

    // 邮箱
    @TableField("email")
    private String email;

    // 手机号
    @TableField("phone_number")
    private String phoneNumber;

    // 昵称
    @TableField("nickname")
    private String nickname;

    // 头像
    @TableField("avatar")
    private String avatar;

    // 注册时间
    @TableField("registration_time")
    private Date registrationTime;

    // 最后登录日期
    @TableField("last_login_date")
    private Date lastLoginDate;

    // 登录错误次数
    @TableField("login_error_count")
    private Integer loginErrorCount;

}