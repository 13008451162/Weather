package com.example.weather.Logic.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.weather.Logic.model.TwentyFourHourWeatherDataModel;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;

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

    @Query("delete from TwentyFourHourWeatherDataModel")
    void deleteAll();

    /**
     * 获取数据链表
     * @return
     */
    @Query("SELECT data FROM TwentyFourHourWeatherDataModel")
    List<HourlyWeatherData.HourlyDTO> getAllData();

}

