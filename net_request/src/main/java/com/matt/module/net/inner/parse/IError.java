package com.matt.module.net.inner.parse;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class IError {

    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR = 1000;
    public static final String UNKNOWN_ERROR_STRING = "未知错误";
    /**
     * 网络连接错误
     */
    public static final int NETCONNECT_ERROR = 1001;
    public static final String NETCONNECT_ERROR_STRING = "网络连接错误";
    /**
     * 服务器异常
     */
    public static final int SERVER_ERROR = 1002;
    public static final String SERVER_ERROR_STRING = "服务器异常";
    /**
     * try catch异常
     */
    public static final int EXCEPTION = 1003;
    public static final String EXCEPTION_STRING = "异常";
    /**
     * 接口请求异常
     */
    public static final int REQUESTDATA_ERROR = 1004;
    public static final String REQUESTDATA_ERROR_STRING = "接口请求异常";
    /**
     * 解析请求异常
     */
    public static final int JSONPARE_ERROR = 1005;
    public static final String JSONPARE_ERROR_STRING = "解析异常";
}
