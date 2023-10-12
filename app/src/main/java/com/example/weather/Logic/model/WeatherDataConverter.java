package com.example.weather.Logic.model;

/**
 * 项目名: weather
 * 文件名: WeatherDataConverter
 * 创建者: lukecc0
 * 创建时间:2023/10/12 下午2:01
 * 描述: 一个类型转化器，帮助Room进行数据类型的转化
 */

import androidx.room.TypeConverter;

import com.example.weather.Logic.netWorkUtil.HourlyWeatherData;
import com.google.gson.Gson;


public class WeatherDataConverter {
    @TypeConverter
    public static HourlyWeatherData.HourlyDTO fromString(String value) {
        if (value == null) {
            return null;
        }
        return new Gson().fromJson(value, HourlyWeatherData.HourlyDTO.class);
    }

    @TypeConverter
    public static String fromDTO(HourlyWeatherData.HourlyDTO data) {
        if (data == null) {
            return null;
        }
        return new Gson().toJson(data);
    }

}