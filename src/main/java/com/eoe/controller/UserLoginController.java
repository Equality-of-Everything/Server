package com.eoe.controller;

import com.eoe.entity.UserLogin;
import com.eoe.result.Code;
import com.eoe.result.Result;
import com.eoe.service.UserLoginService;
import com.eoe.utils.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody UserLogin userLogin) {
        boolean flag = userLoginService.register(userLogin);
        if(flag) return new Result(flag, "注册成功", null);
        return new Result(flag, "注册失败,用户名重复", null);
    }


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody UserLogin userLogin) {
        boolean flaglogin = userLoginService.login(userLogin);
        boolean flagcheckUserName = userLoginService.checkUsername(userLogin.getUsername());
        if(!flagcheckUserName) return new Result(flagcheckUserName, "用户名不存在", null, Code.LOGIN_ERROR_NOUSER);
        if(!flaglogin) return new Result(flaglogin, "登录失败", null, Code.LOGIN_ERROR_PASSWORD);
        String token = JWTTokenUtil.generateToken(userLogin);
        return new Result(true, "登录成功", token);
    }





}
