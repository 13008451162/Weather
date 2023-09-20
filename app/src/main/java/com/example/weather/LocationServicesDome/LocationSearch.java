package com.example.weather.LocationServicesDome;

import android.widget.Toast;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.WeatherApplication;

/**
 * 项目名: weather
 * 文件名: funs
 * 创建者: lukecc0
 * 创建时间:2023/9/17 下午9:38
 * 描述: TODO
 */

public class LocationSearch {


    private double latitude;    //维度
    private double longitude;   //精度

    /**
     * @param city     = 城市
     * @param district = 区县
     * @return 描述：传入城市地址，得到经纬度。
     */
    public void Search(String city, String district) {
        boolean SuccessOrFail = true;   //true表示成功返回经纬度

        GeoCoder mCoder = GeoCoder.newInstance();  //编码器
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {

            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (null != geoCodeResult && null != geoCodeResult.getLocation()) {

                    if (geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                        // 处理错误情况
                        LogUtil.e("FindlocData", "地理编码错误: " + geoCodeResult.error);
                        Toast.makeText(WeatherApplication.getContext(), "地理位置出错了，请稍后再试！", Toast.LENGTH_SHORT).show();
                    } else {
                        latitude = geoCodeResult.getLocation().latitude;
                        longitude = geoCodeResult.getLocation().longitude;
                        LogUtil.d("FindlocData", "纬度: " + latitude + ", 经度: " + longitude);
                        // 在这里处理正确的结果
                    }
                }
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                // 处理反向地理编码结果
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有找到检索结果
                    Toast.makeText(WeatherApplication.getContext(), "地理位置出错了，请稍后再试！", Toast.LENGTH_SHORT).show();
                } else {
                    //详细地址
                    String address = reverseGeoCodeResult.getAddress();
                    //行政区号
                    int adCode = reverseGeoCodeResult.getCityCode();
                    LogUtil.d("FindlocData", address + " " + adCode);
                }
            }
        };

        mCoder.setOnGetGeoCodeResultListener(listener);

        // 城市和地址是必填项
        mCoder.geocode(new GeoCodeOption()
                .city(city)
                .address(district));

        /**
         *
         *     //通过经纬度获取地址
         *     LatLng latLng = new LatLng(latitude,longitude);
         *     mCoder.reverseGeoCode(new ReverseGeoCodeOption()
         *             .location(latLng)
         *             // 设置是否返回新数据 默认值0不返回，1返回
         *             .newVersion(1)
         *             // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
         *             .radius(500));
         *
         */

//        //通过经纬度获取地址
//        LatLng latLng = new LatLng(34.163290267304646, 108.91358047235403);
//        mCoder.reverseGeoCode(new ReverseGeoCodeOption()
//                .location(latLng)
//                // 设置是否返回新数据 默认值0不返回，1返回
//                .newVersion(1)
//                // POI召回半径，允许设置区间为0-1000米，超过1000米按1000米召回。默认值为1000
//                .radius(500));

        //释放内存
        mCoder.destroy();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
