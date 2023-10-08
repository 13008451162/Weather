package com.example.weather.Logic.netWorkUtil;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: HourlyWeatherUtility
 * 创建者: lukecc0
 * 创建时间:2023/10/7 下午9:08
 * 描述: TODO
 */

public class HourlyWeatherUtility extends GenericUtility {

    public HourlyWeatherUtility(String jsonData) {
        super(jsonData);
    }

    public HourlyWeatherUtility() {
    }

    @Override
    protected GenericUtility createUtility(String jsonData) {
        return new HourlyWeatherUtility(jsonData);
    }

    @Override
    protected boolean handJsonParse() {
        try {
            Gson gson = new Gson();
            data = gson.fromJson(jsonData, HourlyWeatherData.class);
            return true;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<HourlyWeatherData.HourlyDTO> getDataList() {
        return ((HourlyWeatherData)data).getHourly();
    }
}
