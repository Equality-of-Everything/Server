package com.eoe.controller;

import com.eoe.entity.FriendShare;
import com.eoe.result.Result;
import com.eoe.service.FriendShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/friendshare")
@Slf4j
@Api(tags = "朋友圈相关接口")
public class FriendShareController {


    @Autowired
    private FriendShareService friendShareService;
    @PostMapping("/upload")
    @ApiOperation("上传朋友圈")
    public Result uploadFriendShare(FriendShare friendShare, MultipartFile[] files) {

        boolean flag = friendShareService.uploadFriendShare(friendShare, files);
        if (flag) {
            return new Result(true, "朋友圈上传成功", null);
        } else {
            return new Result(false, "朋友圈上传失败", null);
        }

    }




}
