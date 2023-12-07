package com.eoe.controller;

import cn.hutool.extra.mail.MailUtil;
import com.eoe.result.Result;
import com.eoe.service.MailCodeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/5 9:58
 * @Decription : 用于发送邮箱验证码，实现密码重置
 */

@RestController
@RequestMapping("/mail")
@Slf4j
@Api(tags = "发送邮箱验证码")
public class SendMailCodeController {

    @Autowired
    private MailCodeService mailCodeService;

    /**
     * 用于客户端获取验证码
     * @param mail
     * @return
     */
    @GetMapping ("/sendCode")
    public Result sendMailCode(String mail) {
       try{
           String code = mailCodeService.generateMailCode(mail);
           String theme = "你的验证码信息如下:";
           MailUtil.send(mail,theme , "你的验证码如下:"+code+",该验证码主要是用户重置行疆客户端密码," +
                   "请尽快输入,你的验证码将于1分钟之后失效啦! 千万不要告诉别人哦！", false);
           return new Result(true,"验证码发送成功",null,200);
       }catch (Exception e){
           log.error("发送邮箱验证码失败",e);
           return new Result(false,"发生未知错误，验证码发送失败",null,500);
       }
    }

    /**
     * 校验验证码
     * @param mail
     * @param code
     * @return
     */
    @PostMapping ("/checkCode")
    public Result checkCode(String mail,String code) {
        if(mailCodeService.checkCode(mail,code)) return new Result(true,"验证码正确",null,200);

        return new Result(false,"验证码错误或已失效",null,400);

    }

}
