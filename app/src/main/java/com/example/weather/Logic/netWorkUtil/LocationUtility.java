package com.example.weather.Logic.netWorkUtil;

import androidx.annotation.NonNull;

import com.example.weather.Ui.Place.PlaceViewModel.DataCallback;
import com.google.gson.Gson;

import java.io.IOException;
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

public class LocationUtility extends GenericUtility<LocationData> {

    public LocationUtility(String jsonData) {
        super(jsonData);
    }

    public LocationUtility() {
    }

    @Override
    protected LocationUtility createUtility(String jsonData) {
        return new LocationUtility(jsonData);
    }


    /**
     * 解析服务器返回的地址json数据
     * @return 是否成功解析
     */
    @Override
    protected boolean handJsonParse() {
        //获取Gson实例
        Gson gson = new Gson();

        try {
            // 使用 Gson 解析 JSON 数据,将JSON数据解析为LocationData对象
            data = gson.fromJson(jsonData, LocationData.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<LocationData.LocationDTO> getDataList() {
        return data.getLocation();
    }
}
