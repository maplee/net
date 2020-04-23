package com.matt.module.demo;

import com.matt.module.demo.request.GetCodeRequest;
import com.matt.module.net.inner.parse.InstectResponse;
import com.matt.module.net.openapi.IResponse;
import com.matt.module.net.openapi.NetApi;

/**
 * Author:Created by matt on 2020/3/3.
 * Email:jiagfone@163.com
 */

public class TestModel {

    public void getCode(GetCodeRequest request , IResponse<String> responseCallBack){
        NetApi.getApiDefault(MethodService.class).getSmsCode(request).enqueue(new InstectResponse(responseCallBack));
    }

    public void getCode1(GetCodeRequest request , IResponse<String> responseCallBack){
        NetApi.getApiCustom("http://check.app.32solo.com",MethodService.class).getSmsCode(request).enqueue(new InstectResponse(responseCallBack));
    }
}
