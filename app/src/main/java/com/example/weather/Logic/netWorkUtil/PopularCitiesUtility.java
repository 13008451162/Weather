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
 * 文件名: PopularCitiesUtility
 * 创建者: lukecc0
 * 创建时间:2023/9/22 下午9:39
 * 描述: 用于模获取热门城市位置等地理信息
 */

public class PopularCitiesUtility extends GenericUtility {

    public PopularCitiesUtility(String jsonData) {
        super(jsonData);
    }

    public PopularCitiesUtility() {
    }

    @Override
    protected GenericUtility createUtility(String jsonData) {
        return new PopularCitiesUtility(jsonData);
    }

    @Override
    protected boolean handJsonParse() {
        //获取Gson实例
        Gson gson = new Gson();

        try {
            // 使用 Gson 解析 JSON 数据,将JSON数据解析为PopularCitiesData对象
            data = gson.fromJson(jsonData, PopularCitiesData.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PopularCitiesData.TopCityListDTO> getDataList() {
        return ((PopularCitiesData)data).getTopCityList();
    }


}
