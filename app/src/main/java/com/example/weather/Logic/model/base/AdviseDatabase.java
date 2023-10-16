package com.example.weather.Logic.model.base;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weather.Logic.dao.AdviseDataDao;
import com.example.weather.Logic.model.AdviseDataModel;
import com.example.weather.Logic.model.Converter.AdviseDataConverter;
import com.example.weather.Logic.model.Converter.SeverDayWeatherDataConverter;
import com.example.weather.Logic.model.SevenDayWeatherDataModel;

/**
 * 项目名: weather
 * 文件名: AdviseDatabase
 * 创建者: lukecc0
 * 创建时间:2023/10/16 下午9:56
 * 描述: 存储天气建议的base
 */
@Database(entities = {AdviseDataModel.class},version = 1)
@TypeConverters(AdviseDataConverter.class)
public abstract class AdviseDatabase extends RoomDatabase {

    public abstract AdviseDataDao WeatherDataDao();

    private static AdviseDatabase instance = null;

    public static synchronized AdviseDatabase getInstance(Context context){
        if(instance == null){
            instance= Room.databaseBuilder(context,AdviseDatabase.class,"AdviseData").build();
        }
        return instance;
    }
}
