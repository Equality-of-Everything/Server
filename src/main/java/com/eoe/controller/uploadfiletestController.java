package com.eoe.controller;

import com.eoe.entity.UserLogin;
import com.eoe.result.Result;
import com.eoe.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("upload")
@Api(tags = "文件上传接口")
@Slf4j
public class uploadfiletestController {


    @Autowired
     private AliOssUtil aliOssUtil;
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam  MultipartFile file) {
        try{
            //原始文件名
            String originalFilename = file.getOriginalFilename();
            //截取原始文件名的后缀 dfdfdf.png
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            //构造新文件名称
            String objectName = UUID.randomUUID().toString() + extension;

            //文件的请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return new Result(true, "上传成功", filePath);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new Result(false, "上传失败", null);
    }


}
