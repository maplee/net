package com.matt.module.demo;

import com.matt.module.net.inner.model.BaseParseModel;
import com.matt.module.net.inner.model.BaseRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Author:Created by matt on 2020/3/3.
 * Email:jiagfone@163.com
 */

public interface MethodService {

    //验证码
    @POST("check/user/smsCode")
    Call<BaseParseModel<String>> getSmsCode(@Body BaseRequest params);
}
