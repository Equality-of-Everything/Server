package com.eoe.service;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/5 9:18
 * @Decription : 用于生成邮箱验证码
 */

public interface MailCodeService {

    /**
     * 用于生成邮箱验证码
     * @return
     */
    public String generateMailCode(String mail);

    /**
     * 用于验证邮箱验证码
     */
    public boolean checkCode(String mail,String code);

}
