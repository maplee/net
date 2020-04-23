package com.matt.module.net.inner.parse;

import android.util.Log;

import com.matt.module.net.inner.CompileConfig;
import com.matt.module.net.openapi.IResponse;
import com.matt.module.net.inner.model.BaseParseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public class InstectResponse<T> implements Callback<BaseParseModel<T>> {


    private static final String TAG = "InstectResponse";

    private IResponse<T> responseCallBack;

    public InstectResponse(IResponse<T> responseCallBack) {
        this.responseCallBack = responseCallBack;
    }
    @Override
    public void onResponse(Call<BaseParseModel<T>> call, Response<BaseParseModel<T>> response) {
        if (responseCallBack == null) return;
        if (CompileConfig.DEBUG) {
            Log.i(TAG, "onResponse: "+response.toString()+",/n"+response.errorBody()+",/n"+response.headers().toString());
        }
        if (response != null && response.body() != null) {
            if (response.code() == 200 || response.code() == 304) {
                try {
                    BaseParseModel<T> baseBean = response.body();
                    if (baseBean == null) {
                        if (CompileConfig.DEBUG) {
                            Log.e(TAG, "httpop-onFailure1: "+IError.UNKNOWN_ERROR_STRING);
                        }
                        responseCallBack.onFailure(new NetException(IError.UNKNOWN_ERROR, IError.UNKNOWN_ERROR_STRING));
                        return;
                    }
                    if (baseBean.getCode() == 999) {
                        if (CompileConfig.DEBUG) {
                            Log.e(TAG, "httpop-onFailure: "+IError.SERVER_ERROR_STRING+",message:"+baseBean.getMsg());
                        }
//                        SharedPrefUtil.setString(RouteApplication.sContext, Constant.SHAREDPREF_TOKEN,"");
//                        SharedPrefUtil.setString(RouteApplication.sContext, Constant.SHAREDPREF_USER,"");
//                        SharedPrefUtil.setBoolean(RouteApplication.sContext, Constant.SHAREDPREF_SAVE_INFO,false);
//                        RouteApplication.interceptLogin();
//                        responseCallBack.onFailure(new NetException(IError.SERVER_ERROR, IError.SERVER_ERROR_STRING+",message:"+baseBean.getMsg()));
//                        return;
                    }
                    responseCallBack.onResponse(baseBean);
                } catch (Exception e) {
                    if (CompileConfig.DEBUG) {
                        Log.e(TAG, "httpop-onFailure: "+e.getMessage());
                    }
                    responseCallBack.onFailure(new NetException(IError.EXCEPTION, e.getMessage()));
                }
            } else {
                if (CompileConfig.DEBUG) {
                    Log.e(TAG, "httpop-onFailure2: "+response.message());
                }
                responseCallBack.onFailure(new NetException(response.code(), response.message()));
            }
        } else {
            if (CompileConfig.DEBUG) {
                Log.e(TAG, "httpop-onFailure3: "+IError.UNKNOWN_ERROR_STRING);
            }
            responseCallBack.onFailure(new NetException(IError.UNKNOWN_ERROR, IError.UNKNOWN_ERROR_STRING));
        }
    }

    @Override
    public void onFailure(Call<BaseParseModel<T>> call, Throwable t) {
        if (CompileConfig.DEBUG) {
            Log.e(TAG, "httpop-onFailure: "+t.toString());
        }
        if (responseCallBack == null) return;
        responseCallBack.onFailure(new NetException(IError.REQUESTDATA_ERROR, t.toString()));
    }
}
