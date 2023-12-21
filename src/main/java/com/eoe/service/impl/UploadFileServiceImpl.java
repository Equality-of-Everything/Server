package com.eoe.service.impl;

import com.eoe.constant.MessageConstant;
import com.eoe.exception.UploadFileFailedException;
import com.eoe.service.UploadFileService;
import com.eoe.utils.AliOssUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private AliOssUtil aliOssUtil;
    @Override
    public String uploadFile(MultipartFile file) {
            try {
                if (!aliOssUtil.verifyImageSzie(file.getSize())) {
                    throw new UploadFileFailedException(MessageConstant.FileSizeExceededException);
                }
                //原始文件名
                String originalFilename = file.getOriginalFilename();

                //截取原始文件名的后缀 dfdfdf.png
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

                // 后缀名限定，只允许上传jpg，png
//                if (!extension.equals(".jpg") && !extension.equals(".png"))
//                    throw new UploadFileFailedException(MessageConstant.InvalidFileFormatException);


                //构造新文件名称
                String objectName = UUID.randomUUID().toString() + extension;

                //文件的请求路径
                String filePath = aliOssUtil.upload(file.getBytes(), objectName);
                return filePath;

            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new UploadFileFailedException(MessageConstant.uploadFailedException);
    }
}
