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
@RequestMapping("/userInfo")
@Api(tags = "用户相关接口")
public class UserInfoController {


    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UploadFileService uploadFileService;

    /**
     * 获取个人页面数据的接口
     *
     * @param username
     * @return
     */
    @PostMapping("/getUserInfo")
    @ApiOperation("获取个人页面数据")
    public Result getUserInfo(String username) {

        UserInfo user = userInfoService.getUserInfo(username);
        if (user == null) {
            return new Result(false, "用户不存在", null);
        } else {
            return new Result(true, "获取成功", user);
        }
    }

    /**
     * 更新个人页面数据
     *
     * @param username
     * @return 只用于更新用户头像
     */
    @PostMapping("/setUserAvatar")
    @ApiOperation("更新用户头像")
    public Result setUserInfoAvatar(@RequestParam String username, @RequestParam MultipartFile file) {

        String avatar = uploadFileService.uploadFile(file);
        boolean flag = userInfoService.setUserAvatarByUsername(username, avatar);
        if (flag) {
            return new Result(true, "更新成功", avatar);
        } else {
            return new Result(false, "更新失败", null);
        }
    }

    /**
     * 更新背景图片数据
     * @param username
     * @param file
     * @return
     */
    @PostMapping("/setUserBackgroundImage")
    @ApiOperation("更新用户背景图片")
    public Result setUserInfoBackgroundImage(@RequestParam String username, @RequestParam MultipartFile file) {

        System.out.println(username);
        String backgroundImage = uploadFileService.uploadFile(file);
        boolean flag = userInfoService.setUserBackgroundImageByUsername(username, backgroundImage);
        if (flag) {
            return new Result(true, "更新成功", backgroundImage);
        } else {
            return new Result(false, "更新失败", null);
        }
    }

    @PostMapping("/getBackgroundImage")
    public Result getBackgroundImageByShareInfoId(@RequestParam String username) {
        String backgroundImage = "";
        backgroundImage = userInfoService.getBackgroundImageByUsername(username);
        if (backgroundImage.length()
                == 0) {
            return new Result(false, "发生未知错误，请稍后重试", null);
        } else {
            return new Result(true, "获取成功", backgroundImage);
        }
    }

    /**
     * 更新个人页面数据
     *
     * @param userInfo
     * @return 只用于更新用户资料
     */
    @PostMapping("/setUserInformation")
    @ApiOperation("更新用户资料")
    public Result updateUserInformation(@RequestBody UserInfo userInfo) {
        boolean flag = userInfoService.updateByName(userInfo);
        if (flag) {
            return new Result(true, "更新成功", null);
        } else {
            return new Result(false, "更新失败", null);
        }
    }

    @GetMapping("/getAvatar/{shareInfoId}")
    public Result getAvatarByShareInfoId(@PathVariable int shareInfoId) {
        String avatar = "";
        avatar = userInfoService.getAvatarByShareInfoId(shareInfoId);
        if (avatar.length() == 0) {
            return new Result(false, "发生未知错误，请稍后重试", null);
        } else {
            return new Result(true, "获取成功", avatar);
        }
    }

    @GetMapping("/getUsername")
    public Result getUsernameByShareInfoId(int shareInfoId) {
        String username = userInfoService.getUsernameByShareInfoId(shareInfoId);
        if(username!=null) return new Result(true, "获取用户名成功", username);
        return new Result(false, "获取用户名失败", null);
    }
}
