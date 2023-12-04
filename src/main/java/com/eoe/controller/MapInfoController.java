package com.eoe.controller;

import com.eoe.entity.MapInfo;
import com.eoe.entity.ShareInfo;
import com.eoe.result.Result;
import com.eoe.service.MapInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.eoe.result.Code.GET_VIDEO_EMPTY;
import static com.eoe.result.Code.GET_VIDEO_SUCCESS;

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

    /**
     * @param mapInfo
     * @return\
     * 用于获取地图背后的视频信息
     */
    @PostMapping("/video")
    @ApiOperation("获取地图背后视频信息")
    public Result getVideo(@RequestBody MapInfo mapInfo) {
        ShareInfo res = mapInfoService.getSourceByPlaceName(mapInfo);
        return res != null? new Result(true, "获取视频信息成功", res,GET_VIDEO_SUCCESS) :
                new Result(false, "本地视频为空", null,GET_VIDEO_EMPTY);

    }

    @PostMapping("/insert")
    @ApiOperation("插入视频信息")
    public Result insertVideo(@RequestBody ShareInfo shareInfo) {
        boolean flag = mapInfoService.insertVideo(shareInfo);
        if (flag) {
            return new Result(true, "插入视频信息成功", null,200);
        } else {
            return new Result(false, "插入视频信息失败", null,400);
        }
    }
}
