package com.matt.module.net.inner.parse.encrypt;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.matt.module.net.inner.CompileConfig;
import com.matt.module.net.inner.NetConstant;
import com.matt.module.net.inner.model.BaseRequest;
import com.matt.module.net.inner.utils.AesUtils;
import com.matt.module.net.openapi.Config;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class InspectEncryptGsonRequestConvert<T> implements Converter<BaseRequest, RequestBody> {

    private Gson gson;
    private final TypeAdapter<T> adapter;
    private Config mConfig;

    public InspectEncryptGsonRequestConvert(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    public InspectEncryptGsonRequestConvert(Gson gson, TypeAdapter<T> adapter, Config config) {
        this.gson = gson;
        this.adapter = adapter;
        this.mConfig = config;
    }

    @Override
    public RequestBody convert(BaseRequest value) throws IOException {
        return RequestBody.create(NetConstant.MEDIA_TYPE, dealRequest(value, true));
    }

    public String dealRequest(BaseRequest request, boolean isEncrypt) {
        if (request == null) {
            return null;
        }
        if (gson == null) {
            gson = new Gson();
        }
        String params = gson.toJson(request);
        if (CompileConfig.DEBUG) {
            Log.i("httpop", "dealRequest: " + params);
        }
        if (!isEncrypt) {
            return params;
        }
        String aesParams = null;
        if (mConfig != null && mConfig.getIEncrypt() != null) {
            aesParams = mConfig.getIEncrypt().encrypt(params);
        } else {
            aesParams = AesUtils.encrypt(params);
        }
        if (CompileConfig.DEBUG) {
            Log.i("httpop", "dealRequest:aes: " + aesParams);
        }
        return aesParams;
    }
}