package com.example.weather.Logic;

import com.example.weather.Logic.dao.AdviseDataDao;
import com.example.weather.Logic.dao.SeverDayWeatherDataDao;
import com.example.weather.Logic.dao.TwentyFourHourWeatherDataDao;
import com.example.weather.Logic.model.AdviseDataModel;
import com.example.weather.Logic.model.SevenDayWeatherDataModel;
import com.example.weather.Logic.model.TwentyFourHourWeatherDataModel;
import com.example.weather.Logic.model.base.AdviseDatabase;
import com.example.weather.Logic.model.base.SeverDayWeatherDatabase;
import com.example.weather.Logic.model.base.TwentyFourHourWeatherDatabase;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.AdviseData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;

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
    public static SeverDayWeatherDatabase dpDayWeatherDatabase;

    public static AdviseDatabase dpAdviseDatabase;

    /**
     * 使用Room保存24小时的天气情况
     * @param dataList 需要保存的数据
     */
    public static void HourSplitList(List<HourlyWeatherData.HourlyDTO> dataList) {

        TwentyFourHourWeatherDataDao dao = WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao();

        dao.deleteAll();

        for (HourlyWeatherData.HourlyDTO dto : dataList) {

            //添加数据
            TwentyFourHourWeatherDataModel twentyFourHourWeatherDataModel = new TwentyFourHourWeatherDataModel();
            twentyFourHourWeatherDataModel.data = dto;
            dao.insertData(twentyFourHourWeatherDataModel);
        }
    }

    /**
     * 使用Room保存近7天的天气情况
     * @param dataList 需要保存的数据
     */
    public static void DaySplitList(List<SevenDayWeatherData.DailyDTO> dataList){

        SeverDayWeatherDataDao dao = WeatherDataInquireTool.dpDayWeatherDatabase.weatherDataDao();
        dao.deleteAll();

        for(SevenDayWeatherData.DailyDTO dto : dataList){

            //添加数据
            SevenDayWeatherDataModel sevenDayWeatherDataModel = new SevenDayWeatherDataModel();
            sevenDayWeatherDataModel.data = dto;
            dao.insertData(sevenDayWeatherDataModel);
        }
    }

    /**
     * 使用Room保存当日的天气建议
     * @param dataList 需要保存的数据
     */
    public static void AdviseSplitList(List<AdviseData.DailyDTO> dataList){
        AdviseDataDao dao = WeatherDataInquireTool.dpAdviseDatabase.WeatherDataDao();
        dao.deleteAll();

        for(AdviseData.DailyDTO dto : dataList){
            //添加数据
            AdviseDataModel adviseDataModel = new AdviseDataModel();
            adviseDataModel.data = dto;
            dao.insertData(adviseDataModel);
        }
    }



}
