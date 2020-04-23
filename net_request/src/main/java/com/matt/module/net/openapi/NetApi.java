package com.matt.module.net.openapi;

import android.content.Context;
import android.support.annotation.NonNull;

import com.matt.module.net.inner.InspectApi;
import com.matt.module.net.inner.NetConstant;

/**
 * Author:Created by matt on 2020/3/3.
 * Email:jiagfone@163.com
 */

public class NetApi {


    public static void init(@NonNull Context context) {
        NetConstant.init(context);
    }

    public static void setDefault(String domain) {
        NetConstant.setDomain(domain);
    }

    public static <T> T getApiDefault(Class<T> service) {
        return InspectApi.getDefault(service);
    }

    public static <T> T getApiCustom(String domain, Class<T> service) {
        return InspectApi.getCustom(domain, service);
    }

    public static Config initDomain(String domain) {
        return InspectApi.init(domain);
    }


    public static void setToken(String domain,String token){
        Config config = InspectApi.getConfig(domain);
        if(config == null){
            throw new RuntimeException("Please init domain:"+domain);
        }
        config.setToken(token);
    }


}
