package com.eoe.result;

import lombok.AllArgsConstructor;
import lombok.Data;

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

}
