package com.matt.module.net.inner.parse.encrypt;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.matt.module.net.openapi.Config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class InspectEncryptGsonConvertFactory extends Converter.Factory {


    private Config mConfig;
    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static InspectEncryptGsonConvertFactory create() {
        return create(new Gson());
    }

    public void setConfig(Config mConfig) {
        this.mConfig = mConfig;
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static InspectEncryptGsonConvertFactory create(Gson gson) {
        return new InspectEncryptGsonConvertFactory(gson);
    }

    private final Gson gson;

    private InspectEncryptGsonConvertFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new InspectEncryptGsonResponseConvert<>(gson,adapter,mConfig);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new InspectEncryptGsonRequestConvert<>(gson, adapter,mConfig);
    }
}