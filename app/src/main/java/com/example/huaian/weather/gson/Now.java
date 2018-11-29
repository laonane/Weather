package com.example.huaian.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description: 实时天气
 * Created by HuaiAn
 * on 2018/11/29 16:36
 */
public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More {

        @SerializedName("txt")
        public String info;
    }
}
