package com.eoe.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/16 21:09
 * @Decription : 注册失败异常类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterFailException extends RuntimeException {
    private String msg;

}
