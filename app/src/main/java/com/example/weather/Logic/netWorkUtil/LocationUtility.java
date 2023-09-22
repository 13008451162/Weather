package com.example.weather.Logic.netWorkUtil;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.weather.TestTool.LogUtil;
import com.example.weather.WeatherApplication;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名: weather
 * 文件名: LocationUtility
 * 创建者: lukecc0
 * 创建时间:2023/9/20 下午3:52
 * 描述: 用于模糊搜索获取位置等地理信息
 */

public class LocationUtility {
    private LocationData locationData;  //
    private String jsonData; //地区的Json数据

    public LocationUtility(String jsonData) {
        this.jsonData = jsonData;
    }

    /**
     * 解析服务器返回的地址json数据
     * @return 是否成功解析
     */
    private boolean handleLocationResponse() {
        //获取Gson实例
        Gson gson = new Gson();

        try {
            // 使用 Gson 解析 JSON 数据,将JSON数据解析为WeatherData对象
            locationData = gson.fromJson(jsonData, LocationData.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //返回地址信息的链表
    public List<LocationData.LocationDTO> getLocationDataList() {
        return locationData.getLocation();
    }

    //使用final保证接口不会被异常修改
    public static void SendAddress(String address,final LocationDataCallback callback) {
        HttpUtil.SendOkhttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //得到服务器返回内容，判断是否访问到服务器
                if (response.isSuccessful()) {
                    //从服务器获取数据进行解析
                    String responseBody = response.body().string();
                    LocationUtility utility;
                    try {
                        utility = new LocationUtility(responseBody);
                        //判断是否成功解析数据，若成功解析则回调返回数据
                        if(utility.handleLocationResponse()){
                            List<LocationData.LocationDTO> LocationList = utility.getLocationDataList();
                            callback.onSuccess(LocationList);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        //释放对象，防止内存泄漏
                        utility = null;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                //处理请求失败的异常情况
                callback.onFailure(e);
            }
        });
    }
}
