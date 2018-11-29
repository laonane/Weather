package com.example.huaian.weather.base;

/**
 * Description: 查询操作
 * Created by HuaiAn
 * on 2018/11/29 09:47
 */
public class BaseQuery {

    /**
     * 查询省市县信息接口
     */
    public interface Location{

        /**
         * 查询全国所有的省份
         */
        void queryProvinces();

        /**
         * 查询某省的所有城市
         */
        void queryCities();

        /**
         * 查询某城市的所有乡镇
         */
        void queryCounties();

        /**
         * 从数据库查询省市县的数据
         * @param address
         * @param type
         */
        void queryFormServer(String address, final String type);
    }
}
