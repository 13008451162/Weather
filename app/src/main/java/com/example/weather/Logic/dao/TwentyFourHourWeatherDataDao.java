package com.example.weather.Logic.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weather.Logic.model.TwentyFourHourWeatherDataModel;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: WeatherDatadao
 * 创建者: lukecc0
 * 创建时间:2023/10/8 下午9:33
 * 描述: 保存24小时天气情况TwentyFourHourWeatherData的Dao层
 */

@Dao
public interface TwentyFourHourWeatherDataDao {

    @Insert
    void insertData(TwentyFourHourWeatherDataModel...weatherData);

    @Query("DELETE FROM TwentyFourHourWeatherDataModel WHERE IdLocation = :locationId")
    void deleteDataByLocation(String locationId);

    @Query("SELECT data FROM TwentyFourHourWeatherDataModel WHERE IdLocation = :locationId")
    List<HourlyWeatherData.HourlyDTO> getDataByLocation(String locationId);

    @Query("UPDATE TwentyFourHourWeatherDataModel SET data = :newData WHERE IdLocation = :locationId")
    void updateData(String locationId, HourlyWeatherData.HourlyDTO newData);

}

