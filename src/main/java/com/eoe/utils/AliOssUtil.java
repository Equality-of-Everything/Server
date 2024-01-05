package com.eoe.utils;



import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Data
@Slf4j
@Component
@Configuration
public class AliOssUtil {

    // 最大图片文件大小位10MB
    private static final int IMG_MAX_FILE_SIZE = 1024 * 1024 * 100; // 10MB

    // 最大视频文件
    private static final int VIED_MAX_FILE_SIZE   = 1024 * 1024 * 100; // 100MB

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    public AliOssUtil(@Value("${alioss.endpoint}") String endpoint,
                      @Value("${alioss.access-key-id}") String accessKeyId,
                      @Value("${alioss.access-key-secret}") String accessKeySecret,
                      @Value("${alioss.bucket-name}") String bucketName) {
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
    }
    /**
     * 文件上传
     *
     * @param bytes
     * @param objectName
     * @return
     */
    public String upload(byte[] bytes, String objectName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        //文件访问路径规则 https://BucketName.Endpoint/ObjectName
        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        String url = stringBuilder.toString();
        //这里做的处理是将第二个http://去掉，不然地址不正确
        int startIndex = url.indexOf("https://", "https://".length());
        String modifiedUrl = url.substring(0, startIndex) + url.substring(startIndex + "https://".length());



        log.info("文件上传到:{}", modifiedUrl);

        return modifiedUrl;
    }


    /**
     * 验证图片大小，最大不超过10MB
     * @param bytes
     * @return
     */
    public boolean verifyImageSzie(long bytes) {
        if (bytes > IMG_MAX_FILE_SIZE) {
            return false;
        }
        return true;
    }


    /**
     * 验证视频大小，最大不超过100MB
     * @param bytes
     * @return
     */
    public boolean verifyVideoSize(long bytes) {
        if (bytes > VIED_MAX_FILE_SIZE) {
            return false;
        }
        return true;
    }

}
