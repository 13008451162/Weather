package com.example.weather.Logic.model.base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weather.Logic.dao.TwentyFourHourWeatherDataDao;
import com.example.weather.Logic.model.Converter.TwentyFourWeatherDataConverter;
import com.example.weather.Logic.model.TwentyFourHourWeatherDataModel;

/**
 * 项目名: weather
 * 文件名: WeatherDatabase
 * 创建者: lukecc0
 * 创建时间:2023/10/8 下午10:25
 * 描述: 存储24小时天气情况的base
 */

@Database(entities = {TwentyFourHourWeatherDataModel.class}, version = 1)
@TypeConverters(TwentyFourWeatherDataConverter.class)
public abstract class TwentyFourHourWeatherDatabase extends RoomDatabase {
    public abstract TwentyFourHourWeatherDataDao weatherDataDao();
    private static TwentyFourHourWeatherDatabase instance = null;

    // 单例模式
    public static synchronized TwentyFourHourWeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, TwentyFourHourWeatherDatabase.class, "TwentyFourHourWeatherData").build();
        }
        return instance;
    }

}


