package com.matt.module.net.inner;

import android.text.TextUtils;
import android.util.Log;

import com.matt.module.net.inner.parse.InspectGsonConvertFactory;
import com.matt.module.net.inner.parse.encrypt.InspectEncryptGsonConvertFactory;
import com.matt.module.net.openapi.Config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class InspectApi {

    private static final String TAG = "httpop";

    private static Map<String, OkHttpClient> sOkHttpClientMap = new HashMap<>();
    private static Map<String, Retrofit> sRetrofitMap = new HashMap<>();
    private static Map<String, Config> sConfigMap = new HashMap<>();

    public static Config init(String domain) {
        if (sConfigMap == null) {
            sConfigMap = new HashMap<>();
        }
        Config config = sConfigMap.get(domain);
        if (config == null) {
            config = new Config(domain);
            sConfigMap.put(domain, config);
        }

        return sConfigMap.get(domain);
    }

    public static Config getConfig(String domain) {
        if (sConfigMap != null && sConfigMap.containsKey(domain)) {
            return sConfigMap.get(domain);
        }
        return null;
    }


    public static <T> T getDefault(Class<T> service) {
        if (TextUtils.isEmpty(NetConstant.DOMAIN)) {
            throw new RuntimeException("domain is null");
        }
        return getRetrofitInst(getInstance(NetConstant.DOMAIN), NetConstant.DOMAIN).create(service);
    }

    public static <T> T getCustom(String domain, Class<T> service) {
        return getRetrofitInst(getInstance(domain), domain).create(service);
    }

    private static Retrofit getRetrofitInst(OkHttpClient okHttpClient, String domain) {
        if (sRetrofitMap == null) {
            sRetrofitMap = new HashMap<>();
        }
        Retrofit mRetrofit = sRetrofitMap.get(domain);
        if (mRetrofit == null) {
            Retrofit.Builder builder = new Retrofit.Builder().client(okHttpClient);
            Config config = sConfigMap.get(domain);
            if (config != null && config.isEncrypt()) {
                InspectEncryptGsonConvertFactory factory = InspectEncryptGsonConvertFactory.create();
                factory.setConfig(config);
                builder.addConverterFactory(factory);
            } else {
                builder.addConverterFactory(InspectGsonConvertFactory.create());
            }
            mRetrofit = builder.baseUrl(domain).build();
        }
        return mRetrofit;
    }

    private static OkHttpClient getInstance(String domain) {
        if (sOkHttpClientMap == null) {
            sOkHttpClientMap = new HashMap<>();
        }
        OkHttpClient okHttpClient = sOkHttpClientMap.get(domain);
        if (okHttpClient == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            Config config = sConfigMap.get(domain);
            if (config == null) {
                config = new Config(domain);
                sConfigMap.put(domain, config);
            }
            okHttpClient = builder.connectTimeout(NetConstant.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)
                    .addInterceptor(new CustomInterceptor(config))
                    .build();
        }
        return okHttpClient;
    }


    static class CustomInterceptor implements Interceptor {

        private Config mConfig;

        public CustomInterceptor(Config config) {
            mConfig = config;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder requestBuilder = chain.request().newBuilder();
            String url = chain.request().url().toString();
            if (CompileConfig.DEBUG) {
                Log.i(TAG, "intercept: " + url);
            }
            if (checkTokenUrl(url)) {
                if (!TextUtils.isEmpty(mConfig.getToken())) {
                    requestBuilder.addHeader("lf-None-Matoh", mConfig.getToken());
                }
            }

            requestBuilder.url(chain.request().url());

            return chain.proceed(requestBuilder.build());
        }

        private boolean checkTokenUrl(String url) {
            if (!mConfig.isNeedToken()) {
                return false;
            }
            if (TextUtils.isEmpty(url)) {
                return false;
            }
            if (mConfig.getIgnoreUrlList() == null || mConfig.getIgnoreUrlList().isEmpty()) {
                return true;
            }
            for (String tempUrl : mConfig.getIgnoreUrlList()) {
                if (url.contains(tempUrl)) {
                    return false;
                }
            }
            return true;
        }
    }


}
