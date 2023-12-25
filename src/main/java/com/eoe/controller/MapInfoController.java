package com.eoe.controller;

import com.eoe.entity.Comment;
import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;
import com.eoe.result.Result;
import com.eoe.service.MapInfoService;
import com.eoe.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.eoe.result.Code.*;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/4 14:36
 * @Decription : 用于获取地图点击所需要的信息
 */

@RestController
@RequestMapping("/map")
@Slf4j
@Api(tags = "地图相关接口")
public class MapInfoController {

    @Autowired
    private MapInfoService mapInfoService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * @param mapInfo
     * @return\
     * 用于获取地图背后的视频信息
     */
    @PostMapping("/video")
    @ApiOperation("获取地图背后视频信息")
    public Result getVideo(@RequestBody MapInfo mapInfo) {
        List<ShareInfo> res = mapInfoService.getSourceByPlaceName(mapInfo);
        return res.size() != 0 ? new Result(true, "获取视频信息成功", res,GET_VIDEO_SUCCESS) :
                new Result(false, "本地视频为空", null,GET_VIDEO_EMPTY);

    }

    @PostMapping("/uploadVideo")
    @ApiOperation("插入视频信息")
    public Result insertVideo(String city, MultipartFile video,String username,String longitude,String latitude) {
        boolean flag = mapInfoService.insertVideo(city, video, username,longitude,latitude);
        return flag? new Result(true, "上传视频成功", null, 200) : new Result(false, "上传视频失败", null, 400);
    }

    /**
     * 点赞操作
     * @param videoId
     * @param username
     * @return
     */
    @PostMapping("/videos/{videoId}/like")
    @Synchronized
    public Result likeVideo(@PathVariable int videoId, @RequestParam String username) {
        int userId = userInfoService.getUserIdByUsername(username);
        boolean flag = mapInfoService.likeVideo(userId, videoId);
        int likeCount = mapInfoService.likeVideoCount(videoId);
        if (flag) {
            return new Result(true, "点赞操作成功", likeCount,200);
        } else {
            return new Result(false, "取消点赞成功", likeCount,400);
        }
    }

    /**
     * 查询点赞数量
     * @param videoId
     * @return
     */
    @GetMapping("/videos/{videoId}/likecount")
    public Result likeVideoCount(@PathVariable int videoId,@RequestParam("username") String username){
        int likecount = mapInfoService.likeVideoCount(videoId);
        int userId = userInfoService.getUserIdByUsername(username);
        boolean flag = mapInfoService.getLikeStatus(videoId, userId);
        System.out.println("flag:"+flag);
        if(flag) return new Result(true, "查询成功", likecount, VIDEO_HAS_LIKED);
        return new Result(true, "查询成功", likecount, VIDEO_NOT_LIKED);
    }

    /**
     * 评论操作
     * @param comment
     * @return
     */
    @PostMapping("/videos/comment")
    public Result commentVideo(@RequestBody Comment comment){
        boolean flag = mapInfoService.commentVideo(comment);
        if (flag) {
            return new Result(true, "评论成功", null, 200);
        }else {
            return new Result(false, "评论失败", null, 400);
        }
    } 

    @GetMapping("/videos/getcomments")
    public Result getComment(@RequestParam int videoId){
        List<Comment> comments = mapInfoService.getComment(videoId);
        return new Result(true, "查询成功", comments, 200);
    }


}

