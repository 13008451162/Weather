package com.example.weather.LocationServicesDome;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.pb.Loc;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.Ui.Place.Fragment.CityWeatherFragment;
import com.example.weather.Ui.Place.PlaceViewModel.CityWeatherViewModel;

import lombok.Synchronized;

/**
 * 项目名: weather
 * 文件名: MyLocationListener
 * 创建者: lukecc0
 * 创建时间:2023/9/18 下午4:53
 * 描述: 显示当前地图中我的位置,一直获取当前位置的经纬度
 */

public class MyLocationListener extends BDAbstractLocationListener {

    private volatile BDLocation Location;
    private volatile static MyLocationListener instance;

    public MutableLiveData<BDLocation> bdLocationMutableLiveData = new MutableLiveData<>();

    // 私有构造函数，防止外部实例化
    private MyLocationListener() {
    }

    // 获取单例实例的方法
    public static MyLocationListener getInstance() {
        if (instance == null) {
            synchronized (MyLocationListener.class) { // 使用类级别的锁
                if (instance == null) {
                    instance = new MyLocationListener();
                }
            }
        }
        return instance;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {

        Log.d("TAG:", "111");

        if (location == null) {
            Log.d("TAG:", "222");
        }


        String longitude;   //经度
        String latitude;    //纬度

        //获取当前位置
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();


        latitude = String.format("%.2f", location.getLatitude());
//        longitude = String.format("%.2f", location.getLongitude());


        float floatValue = Float.parseFloat(latitude);

        //可能出现为取得定位权限时，传递一个经纬度都是0的位置，所以需要if进行判断
        if (Math.abs(floatValue) > 0 && location != null) {
            bdLocationMutableLiveData.postValue(
                    location
            );
        }

//
//        if (Math.abs(floatValue) > 0 && location != null) {
//
//            Location = location;
//            locationInformationLiveData.postValue(
//                    longitude + "," + latitude
//            );
//        }

    }
}