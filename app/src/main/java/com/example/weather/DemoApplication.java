package com.example.weather;

import android.app.Application;
import android.content.Context;

import androidx.core.content.ContextCompat;

import com.baidu.location.LocationClient;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;

/**
 * 项目名: weather
 * 文件名: MyApplication
 * 创建者: lukecc0
 * 创建时间:2023/9/18 下午4:04
 * 描述: TODO
 */

public class DemoApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        // 获取context
        context = getApplicationContext();

        // 同意Baidu地图SDK的隐私协议,这个非常重要
        SDKInitializer.setAgreePrivacy(getApplicationContext(), true);
        //同意客户端隐私情况
        LocationClient.setAgreePrivacy(true);

        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        // 自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        // 包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    public static Context getContext() {
        return context;
    }
}