package com.example.weather.Logic.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weather.Logic.model.AdviseDataModel;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.AdviseData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: AdviseDataDAo
 * 创建者: lukecc0
 * 创建时间:2023/10/16 下午10:01
 * 描述: 存储天气建议的Dao
 */

@Dao
public interface AdviseDataDao {
    @Insert
    void insertData(AdviseDataModel... weatherData);

    @Query("delete from AdviseDataModel")
    void deleteAll();


    @Query("SELECT data FROM AdviseDataModel")
    List<AdviseData.DailyDTO> getAllData();
}