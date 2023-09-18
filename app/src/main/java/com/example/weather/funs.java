package com.example.weather;

import android.util.Log;

import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

/**
 * 项目名: weather
 * 文件名: funs
 * 创建者: lukecc0
 * 创建时间:2023/9/17 下午9:38
 * 描述: TODO
 */

public class funs {
    void funa(){
        GeoCoder mCoder = GeoCoder.newInstance();

        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (null != geoCodeResult && null != geoCodeResult.getLocation()) {
                    if (geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                        // 处理错误情况
                        Log.e("TAS", "地理编码错误: " + geoCodeResult.error);
                    } else {
                        double latitude = geoCodeResult.getLocation().latitude;
                        double longitude = geoCodeResult.getLocation().longitude;
                        Log.d("TAS", "纬度: " + latitude + ", 经度: " + longitude);
                        // 在这里处理正确的结果
                    }
                }
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                // 处理反向地理编码结果
            }
        };

        mCoder.setOnGetGeoCodeResultListener(listener);

        // 城市和地址是必填项
        mCoder.geocode(new GeoCodeOption()
                .city("北京")
                .address("北京上地十街10号"));
    }
}
