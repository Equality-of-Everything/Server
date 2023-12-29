package com.eoe.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/25 16:23
 * @Decription : 获取不到点赞状态
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetVideoLikesStatusException extends RuntimeException{
    private String msg;
}
