package com.example.huaian.weather.gson;

/**
 * Description: 空气质量指数
 * Created by HuaiAn
 * on 2018/11/29 16:34
 */
public class AQI {

    public AQICity city;

    public class AQICity {

        public String aqi;

        public String pm25;
    }
}
