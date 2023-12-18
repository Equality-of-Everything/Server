package com.eoe.controller;


import com.eoe.entity.UserInfo;
import com.eoe.entity.UserLogin;
import com.eoe.result.Result;
import com.eoe.service.UploadFileService;
import com.eoe.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/userinfo")
@Api(tags = "用户相关接口")
public class UserInfoController {



    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 获取个人页面数据的接口
     * @param userLogin
     * @return
     */
    @GetMapping("/getUserInfo")
    @ApiOperation("获取个人页面数据")
    public Result getUserInfo(@RequestBody UserLogin userLogin){

        UserInfo user =  userInfoService.getUserInfo(userLogin);
        if(user == null){
            return new Result(false, "用户不存在", null);
        }else{
            return new Result(true, "获取成功", user);
        }
    }

    /**
     * 更新个人页面数据
     * @param userInfo
     * @return
     */
    @PostMapping("/setUserInfo")
    @ApiOperation("更新个人页面数据")
    public Result setUserInfo(@RequestBody UserInfo userInfo, @RequestParam MultipartFile file){

        userInfo.setAvatar(uploadFileService.uploadFile(file));
        boolean flag = userInfoService.setUserInfo(userInfo);
        if(flag){
            return new Result(true, "更新成功", null);
        }else{
            return new Result(false, "更新失败", null);
        }
    }

}
