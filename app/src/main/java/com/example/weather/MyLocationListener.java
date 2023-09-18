package com.example.weather;

import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

/**
 * 项目名: weather
 * 文件名: MyLocationListener
 * 创建者: lukecc0
 * 创建时间:2023/9/18 下午4:53
 * 描述: TODO
 */

public class MyLocationListener extends BDAbstractLocationListener {

    private MapView mapView;
    private BaiduMap mBaiduMap;

    private boolean hasMovedToCurrentLocation = false;

    @Override
    public void onReceiveLocation(BDLocation location) {

        //mapView 销毁后不在处理新接收的位置
        if (location == null || mapView == null){
            return;
        }
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        mBaiduMap.setMyLocationData(locData);

        // 检查是否已经执行过移动地图视图的操作
        if (!hasMovedToCurrentLocation) {
            moveMapToCurrentLocation(mBaiduMap, location);
            hasMovedToCurrentLocation = true; // 标记已经执行过
        }
        Log.d("IOSO",locData.toString());
    }

    // 移动地图视图到当前位置
    private void moveMapToCurrentLocation(BaiduMap mBaiduMap, BDLocation location) {
        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    public MyLocationListener(MapView mapView, BaiduMap mBaiduMap) {
        this.mapView = mapView;
        this.mBaiduMap = mBaiduMap;
    }

}
