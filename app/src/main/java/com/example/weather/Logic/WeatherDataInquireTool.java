package com.example.weather.Logic;

import com.example.weather.Logic.model.TwentyFourHourWeatherDataModel;
import com.example.weather.Logic.model.TwentyFourHourWeatherDatabase;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;
import com.example.weather.WeatherApplication;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: WeatherDataInquireTool
 * 创建者: lukecc0
 * 创建时间:2023/10/8 下午10:30
 * 描述: TODO
 */

public class WeatherDataInquireTool {

    public static TwentyFourHourWeatherDatabase dpHourWeatherDatabase;
//    public static SevenDayWeatherDatabase

//    /**
//     * 使用Room保存24小时的天气情况
//     * @param dataList
//     */
//    public void saveTwentyFourHourData(List<HourlyWeatherData.HourlyDTO> dataList) {
//        WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao().deleteAll();
//
//        for (HourlyWeatherData.HourlyDTO dto : dataList) {
//            TwentyFourHourWeatherDataModel weatherData = new TwentyFourHourWeatherDataModel();
//            weatherData.data = dto;
//            WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao().insertData(weatherData);
//        }
//
//    }
//
//    /**
//     * 使用Room保存7天的天气情况
//     * @param dataList
//     */
//    public void saveSevenDayData(List<SevenDayWeatherData.DailyDTO> dataList) {
//        // 实现7天天气数据保存逻辑
//
//    }

}
