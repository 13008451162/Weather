package com.example.weather.Logic.model.base;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weather.Logic.dao.SeverDayWeatherDataDao;
import com.example.weather.Logic.model.Converter.SeverDayWeatherDataConverter;
import com.example.weather.Logic.model.SevenDayWeatherDataModel;

/**
 * 项目名: weather
 * 文件名: SeverDayWeatherDatabase
 * 创建者: lukecc0
 * 创建时间:2023/10/15 下午8:16
 * 描述: 存储7天天气情况的base
 */
@Database(entities = {SevenDayWeatherDataModel.class},version = 2)
@TypeConverters(SeverDayWeatherDataConverter.class)
public abstract class SeverDayWeatherDatabase extends RoomDatabase {
    public abstract SeverDayWeatherDataDao weatherDataDao();

    private static SeverDayWeatherDatabase instance = null;

    //单例模式
    public static synchronized SeverDayWeatherDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context,SeverDayWeatherDatabase.class,"ServerDaySeverData").build();
        }
        return instance;
    }

}
