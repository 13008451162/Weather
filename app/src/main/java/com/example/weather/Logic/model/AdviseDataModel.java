package com.example.weather.Logic.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.weather.Logic.model.Converter.AdviseDataConverter;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.AdviseData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;

/**
 * 项目名: weather
 * 文件名: AdviseDataModel
 * 创建者: lukecc0
 * 创建时间:2023/10/16 下午9:52
 * 描述: 存储天气建议的model层
 */
@Entity
@TypeConverters(AdviseDataConverter.class)
public class AdviseDataModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public AdviseData.DailyDTO data;
}
