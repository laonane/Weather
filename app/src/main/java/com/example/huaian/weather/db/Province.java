package com.example.huaian.weather.db;

import org.litepal.crud.LitePalSupport;

/**
 * Description:
 * Created by HuaiAn
 * on 2018/11/28 16:59
 */
public class Province extends LitePalSupport {

    //  实体都应该有的唯一标识符
    private int id;
    //  省名
    private String provinceName;
    //  省代码
    private int provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
