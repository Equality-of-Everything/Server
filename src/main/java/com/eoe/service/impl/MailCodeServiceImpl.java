package com.eoe.service.impl;

import com.eoe.service.MailCodeService;
import com.eoe.utils.MailCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/5 9:23
 * @Decription : 实现邮箱验证码服务,利用Redis缓存
 */

@Service
public class MailCodeServiceImpl implements MailCodeService {

    @Autowired
    private MailCodeUtil mailCodeUtil;


    @CachePut(value = "mailCode", key = "#mail")
    @Override
    public String generateMailCode(String mail) {
        return mailCodeUtil.generateMailCode();
    }

    @Override
    public boolean checkCode(String mail,String code) {
        return code.equals(mailCodeUtil.getCode(mail));
    }
}
