package com.eoe.controller;

import com.eoe.entity.UserLogin;
import com.eoe.utils.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public String login(@RequestBody UserLogin userLogin) {
        System.out.println(userLogin.toString());
        if (userLogin.getUsername().equals("admin") && userLogin.getPassword().equals("123456")) {
            log.info("登录成功！生成token");
            String token = JWTTokenUtil.generateToken(userLogin);
            return token;
        }

        return "";
    }

}
