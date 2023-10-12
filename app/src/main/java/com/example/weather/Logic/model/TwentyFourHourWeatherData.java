package com.example.weather.Logic.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weather.Logic.model.WeatherDataConverter;
import com.example.weather.Logic.netWorkUtil.HourlyWeatherData;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: WeatherData
 * 创建者: lukecc0
 * 创建时间:2023/10/8 下午9:31
 * 描述: 保存各类天气数据
 */

@Entity
@TypeConverters(WeatherDataConverter.class)
public class TwentyFourHourWeatherData {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public HourlyWeatherData.HourlyDTO data;


}
