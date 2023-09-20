package com.example.weather.Util;

import com.google.gson.Gson;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: LocationUtility
 * 创建者: lukecc0
 * 创建时间:2023/9/20 下午3:52
 * 描述: 用于模糊搜索获取位置等地理信息
 */

public class LocationUtility {
    private LocationData locationData;

    /**
     * 解析服务器返回的地址json数据
     * @param jsonData 天气的Json数据
     * @return 是否成功解析
     */
    private boolean handleLocationResponse(String jsonData){
        //获取Gson实例
        Gson gson = new Gson();

        try{
            // 使用 Gson 解析 JSON 数据,将JSON数据解析为WeatherData对象
            locationData = gson.fromJson(jsonData, LocationData.class);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //返回地址信息的链表
    public List<LocationData.LocationDTO> getLocationDataList() {
        return locationData.getLocation();
    }
}
