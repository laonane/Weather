package com.example.huaian.weather.db;

import org.litepal.crud.LitePalSupport;

/**
 * Description:
 * Created by HuaiAn
 * on 2018/11/28 17:09
 */
public class County extends LitePalSupport {

    //  唯一id
    private int id;
    //  县名
    private String countyName;
    //  天气对应的id
    private int weatherId;
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

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
}
