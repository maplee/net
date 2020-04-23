package com.matt.module.demo;

import android.app.Application;
import android.content.Context;

import com.matt.module.net.openapi.NetApi;

/**
 * Author:Created by matt on 2020/3/3.
 * Email:jiagfone@163.com
 */

public class SampleApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        NetApi.init(getApplicationContext());
        NetApi.setDefault("http://check.app.solo.com");
        NetApi.initDomain("http://check.app.solo.com")
                .setEncrypt(true)
                .setIgnoreUrls("check/user/smsCode")
                .setNeedToken(true);
        NetApi.initDomain("http://update.app.solo.com")
                .setEncrypt(false)
                .setIgnoreUrls("update/user/smsCode")
                .setNeedToken(true);
    }
}
