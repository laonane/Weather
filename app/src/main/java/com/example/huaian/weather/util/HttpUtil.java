package com.example.huaian.weather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Description: 与服务器交互类
 * Created by HuaiAn
 * on 2018/11/28 17:25
 */
public class HttpUtil {

    /**
     *  和服务器交互的类
     * @param address   url地址
     * @param callback  okhttp回调
     */
    public static void HttpOkHttpRequest(String address, okhttp3.Callback callback){

        OkHttpClient okHttpClient = new OkHttpClient();
        //  传入请求地址
        Request request = new Request.Builder().url(address).build();
        //  注册回调相应服务器
        okHttpClient.newCall(request).enqueue(callback);
    }
}
