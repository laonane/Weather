package com.example.huaian.weather.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description: 天气总引用
 * Created by HuaiAn
 * on 2018/11/29 16:50
 */
public class Weather {

    /**
     * 判断状态
     */
    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daliy_forecast")
    public List<Forecast> forecastList;
}
