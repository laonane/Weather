package com.example.huaian.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description: 天气预报
 * Created by HuaiAn
 * on 2018/11/29 16:47
 */
public class Forecast {

    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature {

        public String max;

        public String min;
    }

    public class More {

        @SerializedName("txt_d")
        public String info;
    }
}
