package com.eoe.advice;

import com.eoe.exception.*;
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
        return new Result(false, e.getMsg(), null);
    }

    @ExceptionHandler(UserInfoUpdateAvatarException.class)
    public Result handleUpdateAvatarException(UserInfoUpdateAvatarException e) {
        return new Result(false, e.getMsg(), null);
    }

    @ExceptionHandler(UserInfoUpdateException.class)
    public Result handleException(UserInfoUpdateException e) {
        return new Result(false, e.getMessage(), null);
    }

    @ExceptionHandler(MapInfoUpdateFailException.class)
    public Result handleMapInfoException(MapInfoUpdateFailException e) {
        return new Result(false, e.getMessage(), null);
    }

    @ExceptionHandler(GetVideoLikesStatusException.class)
    public Result handleException(Exception e) {
        return new Result(false, e.getMessage(), null);
    }
}
