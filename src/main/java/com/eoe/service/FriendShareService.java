package com.eoe.service;


import com.eoe.entity.FriendShare;
import org.springframework.web.multipart.MultipartFile;

public interface FriendShareService {

    /**
     * 上传朋友圈
     * @param friendShare
     * @param files
     * @return
     */
    boolean uploadFriendShare(FriendShare friendShare, MultipartFile[] files);

    /**
     * 删除朋友圈
     * @param id
     * @return
     */

}
