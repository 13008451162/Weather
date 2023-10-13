package com.example.weather.Ui.Place.PlaceViewModel;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.Logic.WeatherDataInquireTool;
import com.example.weather.Logic.model.TwentyFourHourWeatherDataModel;
import com.example.weather.Logic.model.TwentyFourHourWeatherDatabase;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherUtility;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherUtility;
import com.example.weather.TestTool.LogUtil;

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
    private Activity requireActivity;

    MutableLiveData<SevenDayWeatherData.DailyDTO> sevenDayWeatherLiveData = new MutableLiveData<>();

    public Activity setRequireActivity() {
        return requireActivity;
    }

    public void setRequireActivity(Activity activity) {
        this.requireActivity = activity;
    }

    /**
     * 获取当前24小时的天气情况
     *
     * @return 返回保存天气情况的链表
     */
    public void getHourlyDTo(String Location, DataCallback<HourlyWeatherData.HourlyDTO> callback) {


        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/weather/24h?location=" + Location + "&key=64f323b501dc410cb7ec4fd1b503aab4";


        HourlyWeatherUtility utility = new HourlyWeatherUtility();
        LogUtil.d("sunwenyu", "dataList.toString()1");

        utility.sendAddress(address, new DataCallback<HourlyWeatherData.HourlyDTO>() {

            @Override
            public void onSuccess(List<HourlyWeatherData.HourlyDTO> dataList) {

                SplitList(dataList);

                callback.onSuccess(dataList);
            }

            @Override
            public void onFailure(IOException e) {
                callback.onFailure(e);
            }

        });
    }

    /**
     * 使用Room保存24小时的天气情况
     *
     * @param dataList
     */
    public void SplitList(List<HourlyWeatherData.HourlyDTO> dataList) {
        LogUtil.d("sunwenyu", "dataList.toString()2");

        if (TwentyFourHourWeatherDatabase.Getinstance() != null) {
            LogUtil.d("sunwenyu", "dataList.toString()3");
            WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao().deleteAll();

            for (HourlyWeatherData.HourlyDTO dto : dataList) {
//            TwentyFourHourWeatherDataModel weatherData = new TwentyFourHourWeatherDataModel();
//            weatherData.data = dto;
//            WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao().insertData(weatherData);
                LogUtil.d("sunwenyu", dto.toString());
            }
        }else {
            LogUtil.d("sunwenyu", dataList.toString());
        }
    }

    /**
     * 获取7日内的天气情况
     *
     * @param Location
     */
    public void getSevenDayWeather(String Location) {
        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/weather/24h?location=" + Location + "&key=64f323b501dc410cb7ec4fd1b503aab4";


        SevenDayWeatherUtility utility = new SevenDayWeatherUtility();

        utility.sendAddress(address, new DataCallback() {
            @Override
            public void onSuccess(List dataList) {

                sevenDayWeatherLiveData.postValue((SevenDayWeatherData.DailyDTO) dataList);
            }

            @Override
            public void onFailure(IOException e) {

            }
        });
    }
}
