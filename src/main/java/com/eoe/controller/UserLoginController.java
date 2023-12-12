package com.eoe.controller;

import com.eoe.entity.UserInfo;
import com.eoe.entity.UserLogin;
import com.eoe.result.Code;
import com.eoe.result.Result;
import com.eoe.service.UserInfoService;
import com.eoe.service.UserLoginService;
import com.eoe.utils.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.concurrent.TimeUnit;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RedisTemplate redisTemplate;
    // 缓存SSO登录验证前缀
    public static final String SSO_LOGIN_PREFIX = "sso_login_";

    @PostMapping("/register")
    @ApiOperation("用户注册")
    @Transactional
    public Result register(@RequestBody UserLogin userLogin) {

            log.info("用户注册: {}", userLogin);
            boolean flagemail = userLoginService.checkEmail(userLogin.getEmail());
            if(flagemail){
                return new Result(flagemail, "邮箱已被注册", null);
            }
            boolean flag = userLoginService.register(userLogin);
            //int i = 1 / 0;
            if(!flag){
                return new Result(flag, "注册失败,用户名重复", null);
            }
            //Integer maxuserId = userInfoService.getMaxUserId();
            Integer userId = userLoginService.getUserIdByUsername(userLogin);
            boolean flag2 = userInfoService.setUserInfo(new UserInfo(userId));
            if(flag && flag2){
                return new Result(flag, "注册成功", null);
            }
            return new Result(flag, "注册失败,个人页面信息初始化失败", null);

    }


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody UserLogin userLogin) {
        log.info("用户登录: {}", userLogin);
        boolean flaglogin = userLoginService.login(userLogin);
        boolean flagcheckUserName = userLoginService.checkUsername(userLogin.getUsername());
        if(!flagcheckUserName) return new Result(flagcheckUserName, "用户名不存在", null, Code.LOGIN_ERROR_NOUSER);
        if(!flaglogin) return new Result(flaglogin, "登录失败", null, Code.LOGIN_ERROR_PASSWORD);
        String token = JWTTokenUtil.generateToken(userLogin);
        // 把这个用户在缓存中的token删除掉
        Object redisToken = redisTemplate.opsForValue().get(SSO_LOGIN_PREFIX + userLogin.getUsername());
        if(redisToken!= null) redisTemplate.delete(SSO_LOGIN_PREFIX + userLogin.getUsername());
        // 存入redis
        redisTemplate.opsForValue().set(SSO_LOGIN_PREFIX+userLogin.getUsername(), token,10, TimeUnit.SECONDS);

        return new Result(true, "登录成功", token);
    }

    /**
     * 用于通过邮箱重置密码
     *
     * @return
     */

    @PostMapping("/resetPassword")
    @ApiOperation("邮箱重置密码")
    public Result resetPassword(String mail, String password) {
        try {
            if (userLoginService.resetPasswordByMail(mail, password))
                return new Result(true, "重置密码成功", null, 200);
            return new Result(false, "该邮箱未注册用户，请先注册", null,400);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误，请稍后重试", null);
        }
    }


}
