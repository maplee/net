package com.matt.module.net.inner.parse;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.matt.module.net.inner.NetConstant;
import com.matt.module.net.inner.model.BaseRequest;
import com.matt.module.net.inner.utils.CommonUtils;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class InspectGsonRequestConvert<T> implements Converter<BaseRequest, RequestBody> {

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    public InspectGsonRequestConvert(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

//    @Override
//    public RequestBody convert(String value) throws IOException {
//        return RequestBody.create(NetConstant.MEDIA_TYPE, value);
//    }

    @Override
    public RequestBody convert(BaseRequest value) throws IOException {
        return RequestBody.create(NetConstant.MEDIA_TYPE, CommonUtils.dealRequest(value,false));
    }
}