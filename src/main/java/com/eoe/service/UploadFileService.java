package com.eoe.service;
import org.springframework.web.multipart.MultipartFile;


public interface UploadFileService {

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}
