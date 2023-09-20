package com.example.weather.LocationServicesDome;

import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.weather.TestTool.LogUtil;

/**
 * 项目名: weather
 * 文件名: MyLocationListener
 * 创建者: lukecc0
 * 创建时间:2023/9/18 下午4:53
 * 描述: 显示当前地图中我的位置,一直获取当前位置的经纬度
 */

public class MyLocationListener extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation location) {

        //获取当前位置
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();

        LogUtil.d("MylocData", "纬度: " + location.getLatitude() + ", 经度: " + location.getLongitude());
    }

}
