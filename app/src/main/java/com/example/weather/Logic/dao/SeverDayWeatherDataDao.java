package com.example.weather.Logic.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weather.Logic.model.SevenDayWeatherDataModel;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;


import java.util.List;
/**
 * 项目名: weather
 * 文件名: SeverDayWeatherDataDao
 * 创建者: lukecc0
 * 创建时间:2023/10/15 下午8:19
 * 描述: 保存7日内的天气情况SeverDayWeatherData的Dao层
 */

@Dao
public interface SeverDayWeatherDataDao {
    @Insert
    void insertData(SevenDayWeatherDataModel... weatherData);

    @Query("delete from SevenDayWeatherDataModel")
    void deleteAll();


    @Query("SELECT data FROM SevenDayWeatherDataModel")
    List<SevenDayWeatherData.DailyDTO> getAllData();
}