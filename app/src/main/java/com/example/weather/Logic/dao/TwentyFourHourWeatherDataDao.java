package com.example.weather.Logic.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.weather.Logic.model.TwentyFourHourWeatherData;
import com.example.weather.Logic.netWorkUtil.HourlyWeatherData;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: WeatherDatadao
 * 创建者: lukecc0
 * 创建时间:2023/10/8 下午9:33
 * 描述: WeatherData的Dao层
 */

@Dao
public interface TwentyFourHourWeatherDataDao {

    @Insert
    void insertData(TwentyFourHourWeatherData...weatherData);

    @Query("SELECT * FROM TwentyFourHourWeatherData WHERE id = :id")
    TwentyFourHourWeatherData getUserName(int id); // 在这里使用相同的参数名

    @Delete
    void delete(TwentyFourHourWeatherData weatherData);

    @Update
    public void update(TwentyFourHourWeatherData weatherData);

    /**
     * 获取数据链表
     * @return
     */
    @Query("SELECT data FROM TwentyFourHourWeatherData")
    List<HourlyWeatherData.HourlyDTO> getAllData();

}

