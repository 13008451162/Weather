package com.example.weather.Logic.netWorkUtil;

import com.google.gson.Gson;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: Utility
 * 创建者: lukecc0
 * 创建时间:2023/9/19 下午3:01
 * 描述: TODO
 */

public class WeatherUtility {

    private WeatherData DayWeatherSum;

    /**
     * 解析服务器返回的天气json数据
     * @param jsonData 天气的Json数据
     * @return 是否成功解析
     */
    private boolean handleWeatherResponse(String jsonData){
        //获取Gson实例
        Gson gson = new Gson();

        try{
            // 使用 Gson 解析 JSON 数据,将JSON数据解析为WeatherData对象
            DayWeatherSum = gson.fromJson(jsonData, WeatherData.class);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //获取每日天气
    public List<WeatherData.DailyDTO> getDailyWeatherList() {
        return DayWeatherSum.getDaily();
    }

    /**
     * 获取总数据，包括以下内容
     * code 请参考状态码
     * updateTime 当前API的最近更新时间
     * fxLink 当前数据的响应式页面，便于嵌入网站或应用
     * @return
     */
    public WeatherData getDayWeatherSum() {
        return DayWeatherSum;
    }
}
