package com.example.weather.Logic.model.Converter;

import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;
import com.google.gson.Gson;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;


/**
 * 项目名: weather
 * 文件名: SeverDayWeatherDataConverter
 * 创建者: lukecc0
 * 创建时间:2023/10/15 下午8:11
 * 描述: 一个类型转化器，帮助Room进行数据类型的转化
 */

public class SeverDayWeatherDataConverter implements DataConverter<SevenDayWeatherData.DailyDTO> {

    @Override
    @TypeConverter
    public SevenDayWeatherData.DailyDTO fromString(String value) {
        if (value == null) {
            return null;
        }
        return new Gson().fromJson(value, SevenDayWeatherData.DailyDTO.class);
    }

    @Override
    @TypeConverter
    public String fromDTO(SevenDayWeatherData.DailyDTO data) {
        if (data == null) {
            return null;
        }
        return new Gson().toJson(data);
    }
}
