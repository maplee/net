package com.matt.module.net.inner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.Dns;

/**
 * Author:Created by matt on 2019/6/10.
 * Email:jiagfone@163.com
 */
public class HttpDns implements Dns {
    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {

        return null;
    }
}
