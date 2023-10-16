package com.example.weather.Logic.netWorkUtil.LocationAndCity;

import com.example.weather.Logic.netWorkUtil.GenericUtility;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.Ui.Place.PlaceViewModel.DataCallback;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: SevenDayWeatherUtility
 * 创建者: lukecc0
 * 创建时间:2023/10/12 下午10:18
 * 描述: 获取近7天数据
 */

public class SevenDayWeatherUtility extends GenericUtility {
    public SevenDayWeatherUtility(String jsonData) {
        super(jsonData);
    }

    public SevenDayWeatherUtility() {
    }

    @Override
    protected GenericUtility createUtility(String jsonData) {
        return new SevenDayWeatherUtility(jsonData);
    }

    @Override
    protected boolean handJsonParse() {
        //获取Gson实例
        Gson gson = new Gson();

        try {
            data = gson.fromJson(jsonData, SevenDayWeatherData.class);
            return true;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List getDataList() {
        return ((SevenDayWeatherData)data).getDaily();
    }

}
