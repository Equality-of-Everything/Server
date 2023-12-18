package com.eoe.advice;

import com.eoe.exception.RegisterFailException;
import com.eoe.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author : Zhang
 * @Date : Created in 2023/12/16 21:13
 * @Decription : 利用Aop捕获异常
 */

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(RegisterFailException.class)
    public Result handleRegisterFailException(RegisterFailException e) {
        return new Result(false,e.getMsg(),null);
    }
}
