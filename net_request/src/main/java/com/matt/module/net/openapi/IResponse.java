package com.matt.module.net.openapi;

import com.matt.module.net.inner.model.BaseParseModel;
import com.matt.module.net.inner.parse.NetException;

/**
 * Author:Created by matt on 2020/3/14.
 * Email:jiagfone@163.com
 */

public interface IResponse<T> {

    void onResponse(BaseParseModel<T> baseParseModel);

    void onFailure(NetException exception);

}
