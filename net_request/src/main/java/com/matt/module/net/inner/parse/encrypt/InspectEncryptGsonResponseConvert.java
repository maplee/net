package com.matt.module.net.inner.parse.encrypt;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.matt.module.net.inner.CompileConfig;
import com.matt.module.net.inner.utils.AesUtils;
import com.matt.module.net.openapi.Config;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class InspectEncryptGsonResponseConvert<T> implements Converter<ResponseBody, T> {
    private static final String TAG = "httpop";
    private final TypeAdapter<T> adapter;
    private Gson gson;
    private Config mConfig;

    public InspectEncryptGsonResponseConvert(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    public InspectEncryptGsonResponseConvert(Gson gson, TypeAdapter<T> adapter, Config config) {
        this.adapter = adapter;
        this.gson = gson;
        this.mConfig = config;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String result = value.string();
        String json = null;
        if (mConfig != null && mConfig.getIEncrypt() != null) {
            json = mConfig.getIEncrypt().decrypt(result);
        } else {
            json = AesUtils.decrypt(result);
        }

        if (CompileConfig.DEBUG) {
            Log.i(TAG, "convert: " + result);
            Log.i(TAG, "convert: d:" + json);
        }
        try {
            return adapter.fromJson(json);
        } finally {
            value.close();
        }

    }
}