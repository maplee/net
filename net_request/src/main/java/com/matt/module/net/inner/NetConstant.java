package com.matt.module.net.inner;

import android.content.Context;

import java.nio.charset.Charset;

import okhttp3.MediaType;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class NetConstant {

    public static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final long DEFAULT_MILLISECONDS = 30 * 1000;

    public static String DOMAIN;

    public static Context sContext;


    public static void init(Context context) {
        sContext = context;
    }

    public static void setDomain(String domain) {
        DOMAIN = domain;
    }
}
