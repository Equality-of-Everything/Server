package com.eoe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author : Zhang
 * @Date : Created in 2023/11/28 21:34
 * @Decription : Jwt token测试类，只有带有token才能访问
 */

@Slf4j
@RestController
public class SecureController {

    @RequestMapping("/secure/getUserInfo")
    public String login(HttpServletRequest request) {
        String userName = request.getAttribute("username").toString();
        String password= request.getAttribute("password").toString();
        return "userName=" + userName+ ",password=" + password;
    }
}
