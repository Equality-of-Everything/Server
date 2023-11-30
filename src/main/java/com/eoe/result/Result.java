package com.eoe.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
@AllArgsConstructor
public class Result<T> implements Serializable {

    private boolean flag; //编码：true成功，false为失败
    private String msg; //错误信息
    private T data; //数据
    private int code; //状态码

    public Result( boolean flag, String msg, T data){
        this.flag = flag;
        this.msg = msg;
        this.data = data;
    }

}
