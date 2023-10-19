package com.example.weather.Logic;

import com.example.weather.LocationServicesDome.MyLocationListener;
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
import com.example.weather.TestTool.LogUtil;

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
     *
     * @param dataList 需要保存的数据
     */
    public static void HourSplitList(List<HourlyWeatherData.HourlyDTO> dataList, String IdLocation) {

        TwentyFourHourWeatherDataDao dao = WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao();

        dao.deleteDataByLocation(IdLocation);

        for (HourlyWeatherData.HourlyDTO dto : dataList) {
            //数据库没有数据，第一次插入数据
            TwentyFourHourWeatherDataModel model = new TwentyFourHourWeatherDataModel();
            model.IdLocation = IdLocation;
            model.data = dto;
            dao.insertData(model);
        }


    }

    /**
     * 使用Room保存近7天的天气情况
     *
     * @param dataList 需要保存的数据
     */
    public static void DaySplitList(List<SevenDayWeatherData.DailyDTO> dataList, String IdLocation) {

        SeverDayWeatherDataDao dao = WeatherDataInquireTool.dpDayWeatherDatabase.weatherDataDao();

        dao.deleteDataByLocation(IdLocation);

        for (SevenDayWeatherData.DailyDTO dto : dataList) {
            //数据库没有数据，第一次插入数据
            SevenDayWeatherDataModel model = new SevenDayWeatherDataModel();
            model.IdLocation = IdLocation;
            model.data = dto;
            dao.insertData(model);
        }

    }

    /**
     * 使用Room保存当日的天气建议
     *
     * @param dataList     需要保存的数据
     * @param IdLocation   位置的Id/经纬度
     * @param locationName 名称
     */
    public static void AdviseSplitList(List<AdviseData.DailyDTO> dataList, String IdLocation, String locationName) {
        AdviseDataDao dao = WeatherDataInquireTool.dpAdviseDatabase.WeatherDataDao();
        dao.deleteDataByLocation(IdLocation);

        for (AdviseData.DailyDTO dto : dataList) {
            //数据库没有数据，第一次插入数据
            AdviseDataModel model = new AdviseDataModel();
            model.IdLocation = IdLocation;

            model.District = locationName;
            if (locationName != null)
           LogUtil.d("JSAA",locationName);
            model.data = dto;
            dao.insertData(model);
        }
    }
}
