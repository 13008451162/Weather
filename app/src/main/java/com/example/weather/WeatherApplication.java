package com.example.weather;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.weather.LocationServicesDome.LocationSearch;
import com.example.weather.LocationServicesDome.MyLocationListener;
import com.example.weather.Logic.WeatherDataInquireTool;
import com.example.weather.Logic.model.base.AdviseDatabase;
import com.example.weather.Logic.model.base.SeverDayWeatherDatabase;
import com.example.weather.Logic.model.base.TwentyFourHourWeatherDatabase;

/**
 * 项目名: weather
 * 文件名: MyApplication
 * 创建者: lukecc0
 * 创建时间:2023/9/18 下午4:04
 * 描述: TODO
 */

public class WeatherApplication extends Application {

    private static Context context;



    //获取初始状态的位置
    private LocationClient mLocationClient;

    private static WeatherApplication weatherApplication;

    //单例模式
    public static  WeatherApplication getInstance(){
        if (weatherApplication == null){
            return new WeatherApplication();
        }
        return weatherApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        // 获取context
        context = this;

        //启动所有的数据库,获取数据库持有
        StartDatabase();

        // 同意Baidu地图SDK的隐私协议,这个非常重要
        SDKInitializer.setAgreePrivacy(getApplicationContext(), true);
        //同意客户端隐私情况
        LocationClient.setAgreePrivacy(true);

        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        // 自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        // 包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        try {
            mLocationClient = new LocationClient(getApplicationContext());
            initMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        LocationSearch locationSearch = new LocationSearch();
        locationSearch.Search("北京","北京上地十街10号");
//        LocationSearch.reverseEncoding(34.163290267304646,108.91358047235403);


    }

    // 初始化地图和定位
    private void initMap() {
        // 定位初始化
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(100_000);
        mLocationClient.setLocOption(option);

        MyLocationListener myLocationListener = MyLocationListener.getInstance();
        mLocationClient.registerLocationListener(myLocationListener);
        //后台不停抓取位置
        mLocationClient.start();
//        CityWeatherViewModel viewModel = new ViewModelProvider(CityWeatherFragment).get();
//        mLocationClient.stop();
    }

    public void StopGetLocation(){
        if(mLocationClient.isStarted()){
            mLocationClient.stop();
        }
    }

    public static Context getContext() {
        return context;
    }


    /**
     * 启动所有的数据库
     */
    private static void StartDatabase(){

        //24小时天气的数据
        WeatherDataInquireTool.dpHourWeatherDatabase = TwentyFourHourWeatherDatabase.getInstance(context);

        //7天内的数据
        WeatherDataInquireTool.dpDayWeatherDatabase = SeverDayWeatherDatabase.getInstance(context);

        //获取当日的建议
        WeatherDataInquireTool.dpAdviseDatabase = AdviseDatabase.getInstance(context);

    }
}