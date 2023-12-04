package com.eoe.result;


/**
 * 用于规范前后端交互的返回码
 */
public class Code {
    public static final int SUCCESS = 200;
    //用户名不存在
    public static final int LOGIN_ERROR_NOUSER = 410;

    //密码错误
    public static final int LOGIN_ERROR_PASSWORD = 411;

    // 获取视频信息成功
    public static final int GET_VIDEO_SUCCESS = 200;

    // 当地视频内容为空
    public static final int GET_VIDEO_EMPTY = 404;

    // Token不存在
    public static final int TOKEN_NOT_EXIST = 401;

    // Token不合法或已过期
    public static final int TOKEN_INVALID = 402;

}
