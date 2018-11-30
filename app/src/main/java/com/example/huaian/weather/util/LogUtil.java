package com.example.huaian.weather.util;

import android.util.Log;

/**
 * @author：Huaian
 * @date：2018/11/29 12:00
 * @description：
 */
public class LogUtil {
    private static final boolean isDebug = false;
    private static final String TAG = "weaterLogUtil--->LOG:";

    public static void w(String logMessage) {
        if (isDebug) {
            Log.w(TAG, logMessage);
        }
    }

    public static void w(String tag, String logMessage) {
        if (isDebug) {
            Log.w(tag, logMessage);
        }
    }


    public static void e(String logMessage) {
        if (isDebug) {
            Log.e(TAG, logMessage);
        }
    }

    public static void e(String tag, String logMessage) {
        if (isDebug) {
            Log.e(tag, logMessage);
        }
    }

    public static void i(String logMsg){
        if (isDebug) {
            Log.i(TAG, logMsg);
        }
    }

    public static void i(String tag, String logMsg){
        if (isDebug) {
            Log.i(tag, logMsg);
        }
    }

    public static void d(String logMsg){
        if (isDebug) {
            Log.d(TAG, logMsg);
        }
    }

    public static void d(String tag, String logMsg){
        if (isDebug) {
            Log.d(tag, logMsg);
        }
    }

}
