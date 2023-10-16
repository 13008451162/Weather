package com.example.weather.Logic.model.Converter;

import androidx.room.TypeConverter;

import com.example.weather.Logic.netWorkUtil.LocationAndCity.AdviseData;
import com.google.gson.Gson;

/**
 * 项目名: weather
 * 文件名: AdviseDataConverter
 * 创建者: lukecc0
 * 创建时间:2023/10/16 下午9:53
 * 描述: 一个类型转化器，帮助Room进行数据类型的转化
 */

public class AdviseDataConverter implements DataConverter<AdviseData.DailyDTO> {
    @Override
    @TypeConverter
    public AdviseData.DailyDTO fromString(String value) {
        if (value == null) {
            return null;
        }

        return new Gson().fromJson(value, AdviseData.DailyDTO.class);
    }

    @Override
    @TypeConverter
    public String fromDTO(AdviseData.DailyDTO data) {
        if (data == null) {
            return null;
        }

        return new Gson().toJson(data);
    }
}
