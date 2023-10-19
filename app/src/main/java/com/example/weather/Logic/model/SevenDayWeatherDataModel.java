package com.example.weather.Logic.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weather.Logic.model.Converter.SeverDayWeatherDataConverter;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;

/**
 * 项目名: weather
 * 文件名: SevenDayWeatherData
 * 创建者: lukecc0
 * 创建时间:2023/10/13 上午10:23
 * 描述: 保存近7天天气的数据
 */
@Entity
@TypeConverters(SeverDayWeatherDataConverter.class)
public class SevenDayWeatherDataModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String IdLocation;

    @ColumnInfo
    public SevenDayWeatherData.DailyDTO data;

}
