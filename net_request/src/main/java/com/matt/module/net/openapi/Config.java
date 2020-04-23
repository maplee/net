package com.matt.module.net.openapi;

import java.util.Arrays;
import java.util.List;

/**
 * Author:Created by matt on 2020/3/3.
 * Email:jiagfone@163.com
 */

public class Config {
    // domain
    private String mDomain;
    // 需要token
    private boolean mNeedToken;
    // token
    private String mToken;
    // 是否加密
    private boolean isEncrypt;
    // 需要token忽略列表
    private List<String> mIgnoreUrlList;

    private IEncrypt mEncrypt;


    public Config(String domain) {
        mDomain = domain;
    }

    public String getDomain() {
        return mDomain;
    }

    public Config setDomain(String domain) {
        mDomain = domain;
        return this;
    }

    public boolean isNeedToken() {
        return mNeedToken;
    }

    public Config setNeedToken(boolean needToken) {
        mNeedToken = needToken;
        return this;
    }

    public String getToken() {
        return mToken;
    }

    public Config setToken(String token) {
        mToken = token;
        return this;
    }

    public boolean isEncrypt() {
        return isEncrypt;
    }

    public Config setEncrypt(boolean encrypt) {
        isEncrypt = encrypt;
        return this;
    }

    public List<String> getIgnoreUrlList() {
        return mIgnoreUrlList;
    }

    public Config setIgnoreUrlList(List<String> ignoreUrlList) {
        mIgnoreUrlList = ignoreUrlList;
        return this;
    }

    public Config setIgnoreUrls(String... urls) {
        mIgnoreUrlList = Arrays.asList(urls);
        return this;
    }

    public IEncrypt getIEncrypt(){
        return mEncrypt;
    }

    public Config setIEncrypt(IEncrypt iEncrypt) {
        mEncrypt = iEncrypt;
        return this;
    }


}
