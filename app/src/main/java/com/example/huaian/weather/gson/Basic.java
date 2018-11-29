package com.example.huaian.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description: 城市基础信息
 * Created by HuaiAn
 * on 2018/11/29 16:31
 */
public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{

        @SerializedName("loc")
        public String updateTime;
    }
}
