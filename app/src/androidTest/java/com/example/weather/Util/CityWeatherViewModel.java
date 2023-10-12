package com.example.weather.Util;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.weather.Logic.WeatherDataInquireTool;
import com.example.weather.Logic.model.TwentyFourHourWeatherData;
import com.example.weather.Logic.netWorkUtil.HourlyWeatherData;
import com.example.weather.Logic.netWorkUtil.HourlyWeatherUtility;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.Ui.Place.PlaceViewModel.DataCallback;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 项目名: weather
 * 文件名: CityWeatherViewModel
 * 创建者: lukecc0
 * 创建时间:2023/10/7 下午10:15
 * 描述: TODO
 */

public class CityWeatherViewModel extends ViewModel {
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * 获取当前24小时的天气情况
     *
     * @return 返回保存天气情况的链表
     */
    @Test
    public void getHourlyDTo(String Location, DataCallback<HourlyWeatherData.HourlyDTO> callback) {

        List<HourlyWeatherData.HourlyDTO> mList;

        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/weather/24h?location=108.91,34.16&key=64f323b501dc410cb7ec4fd1b503aab4";

        Log.e("haojinhui", "getHourlyDTo: " + address);

        HourlyWeatherUtility utility = new HourlyWeatherUtility();

        utility.sendAddress(address, new DataCallback<HourlyWeatherData.HourlyDTO>() {

            @Override
            public void onSuccess(List<HourlyWeatherData.HourlyDTO> dataList) {
                //保存数据到数据库
                SplitList(dataList);
                callback.onSuccess(dataList);
            }

            @Override
            public void onFailure(IOException e) {

            }

//            @Override
//            public void onFailure(List<HourlyWeatherData.HourlyDTO> dataList) {
////                TwentyFourHourWeatherData weatherData =  WeatherDataInquireTool.dp.weatherDataDao()
////                        .getUserName(WeatherDataInquireTool.hourlyWeatherData);
////                callback.onFailure(weatherData.data);
//            }

        });
    }

    public void SplitList(List<HourlyWeatherData.HourlyDTO> dataList){

        for(HourlyWeatherData.HourlyDTO dto : dataList){
            TwentyFourHourWeatherData weatherData = new TwentyFourHourWeatherData();
            weatherData.data = dto;
            LogUtil.d("DATA",dto.toString());
            WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao().insertData(weatherData);
        }

    }
}
