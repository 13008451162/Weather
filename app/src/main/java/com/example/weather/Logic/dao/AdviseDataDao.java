package com.example.weather.Logic.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weather.Logic.model.AdviseDataModel;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.AdviseData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
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

    @Query("DELETE FROM AdviseDataModel WHERE IdLocation = :locationId")
    void deleteDataByLocation(String locationId);

    @Query("SELECT data FROM AdviseDataModel WHERE IdLocation = :locationId")
    List<AdviseData.DailyDTO> getDataByLocation(String locationId);

    @Query("UPDATE AdviseDataModel SET data = :newData WHERE IdLocation = :locationId")
    void updateData(String locationId, AdviseData.DailyDTO newData);

    @Query("SELECT District FROM AdviseDataModel WHERE IdLocation = :targetIdLocation")
    String getDistrictByLocationId(String targetIdLocation);
}