package com.eoe.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/19 8:18
 * @Decription : 用户更新头像异常
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoUpdateAvatarException extends RuntimeException {
    private String msg;
}
