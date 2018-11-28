package com.example.huaian.weather.db;

import org.litepal.crud.LitePalSupport;

/**
 * Description:
 * Created by HuaiAn
 * on 2018/11/28 17:06
 */
public class City extends LitePalSupport {

    //  id
    private int id;
    //  城市名
    private String cityName;
    //  城市编号
    private int cityCode;
    //  省id
    private int provinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
