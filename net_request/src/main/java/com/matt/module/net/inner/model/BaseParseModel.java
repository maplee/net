package com.matt.module.net.inner.model;


/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class BaseParseModel<T> {

    //返回码，0表示成功，非0表示失败
    private int code;
    //返回信息
    private String msg;
    //请求id
    private String requestId;
    //返回数据
    private T data;

    private PageBean page;

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
