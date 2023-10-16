package com.example.weather.Logic.model.Converter;

import androidx.room.TypeConverter;

import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
import com.google.gson.Gson;

/**
 * 项目名: weather
 * 文件名: DataConverter
 * 创建者: lukecc0
 * 创建时间:2023/10/13 上午11:58
 * 描述: TODO
 */

public interface DataConverter<T> {
    /**
     * 获取数据进行
     * @param value
     * @return
     */
    @TypeConverter
     T fromString(String value);

    @TypeConverter
    String fromDTO(T data);

}
