package com.matt.module.net.inner.parse;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.matt.module.net.inner.CompileConfig;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class InspectGsonResponseConvert<T> implements Converter<ResponseBody, T> {
    private static final String TAG = "httpop";
    private final TypeAdapter<T> adapter;
    private Gson gson;

    public InspectGsonResponseConvert(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String result = value.string();
        if (CompileConfig.DEBUG) {
            Log.i(TAG, "convert: " + result);
        }
        try {
            return adapter.fromJson(result);
        } finally {
            value.close();
        }

    }
}