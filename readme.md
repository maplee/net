### Android基于Retrofit2改造的可设置多域名的网络加载框架

#### 1.使用说明

添加仓库

```
allprojects {
    repositories {
        google()
        jcenter()
        maven {url 'https://raw.github.com/maplee/mvn-repo/master'}
    }
}

```

#### 2.添加依赖

```
implementation 'com.matt.module:net:1.0.0'

```


#### 3.集成模块

##### 3.1初始化
在Application中的onCreate中添加

```
NetApi.init(getApplicationContext());
        NetApi.setDefault("http://check.app.solo.com");// 设置默认域名
        // 设置默认域名的相关配置
        NetApi.initDomain("http://check.app.solo.com")
                .setEncrypt(true)// 是否加密
                .setIgnoreUrls("check/user/smsCode")// 加密忽略地址
                .setNeedToken(true);// 请求头是否需要添加token
         // 设置其他域名的相关配置
        NetApi.initDomain("http://update.app.solo.com")
                .setEncrypt(false)
                .setIgnoreUrls("update/user/smsCode")
                .setNeedToken(true);
       //在获取到token后赋值
       NetApi.setToken("djhfj");

```

##### 3.2使用

```
// 默认域名请求
NetApi.getApiDefault(MethodService.class).getSmsCode(request).enqueue(new InstectResponse(responseCallBack));
// 指定域名请求
NetApi.getApiCustom("http://check.app.solo.com",MethodService.class).getSmsCode(request).enqueue(new InstectResponse(responseCallBack));

```

