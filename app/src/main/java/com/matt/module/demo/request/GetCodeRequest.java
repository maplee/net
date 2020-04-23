package com.matt.module.demo.request;

import com.matt.module.net.inner.model.BaseRequest;

import java.io.Serializable;

/**
 * Author:Created by matt on 2020/3/30.
 * Email:jiagfone@163.com
 */

public class GetCodeRequest extends BaseRequest implements Serializable {

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
