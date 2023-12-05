package com.eoe.utils;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/5 16:19
 * @Decription : 邮箱验证码工具类，用于实现邮箱验证码的生成和校验
 */

@Component
public class MailCodeUtil {

    /**
     * 生成6位邮箱验证码
     * @param
     * @return
     */
    public String generateMailCode() {
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    /**
     * 从缓存区中取邮箱验证码
     * @param
     * @return
     */
    @Cacheable(value = "mailCode", key = "#mail")
    public String getCode(String mail) {
        return null;
    }
}
