package com.eoe.service;


import cn.hutool.db.PageResult;
import com.eoe.entity.FriendShare;
import org.springframework.web.multipart.MultipartFile;

public interface FriendShareService {

    /**
     * 上传朋友圈
     * @param friendShare
     * @param files
     * @return
     */
    boolean uploadFriendShare(FriendShare friendShare, String username, MultipartFile[] files);

    /**
     * 删除朋友圈
     * @param id
     * @return
     */
    boolean deleteFriendShare(int id);

    /**
     * 查询朋友圈
     * @return
     */
    PageResult getFriendShareList();
}
