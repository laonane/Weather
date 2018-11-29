package com.example.huaian.weather.db;

import org.litepal.crud.LitePalSupport;

/**
 * Description: 县地区类
 * Created by HuaiAn
 * on 2018/11/28 17:09
 */
public class County extends LitePalSupport {

    //  唯一id
    private int id;
    //  县名
    private String countyName;
    //  天气对应的id
    private String weatherId;
    //  城市id
    private int cityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }
}
