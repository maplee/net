package com.matt.module.demo.request;

import com.matt.module.net.inner.model.BaseRequest;

import java.io.Serializable;

/**
 * Author:Created by matt on 2020/3/30.
 * Email:jiagfone@163.com
 */

public class GetMachineRequest extends BaseRequest implements Serializable {

    private String deviceId;
    private String batcId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBatcId() {
        return batcId;
    }

    public void setBatcId(String batcId) {
        this.batcId = batcId;
    }
}
