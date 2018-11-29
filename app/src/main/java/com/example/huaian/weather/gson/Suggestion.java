package com.example.huaian.weather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Description: 生活建议
 * Created by HuaiAn
 * on 2018/11/29 16:39
 */
public class Suggestion {
    /**
     * 舒适度
     */
    @SerializedName("comf")
    public Comfort comfort;

    /**
     * 洗车指南
     */
    @SerializedName("cw")
    public CarWash carWash;

    /**
     * 运动指数
     */
    public Sport sport;

    public class Comfort{

        @SerializedName("txt")
        public String info;
    }

    public class CarWash {

        @SerializedName("txt")
        public String info;
    }

    public class Sport {

        @SerializedName("txt")
        public String info;
    }
}
