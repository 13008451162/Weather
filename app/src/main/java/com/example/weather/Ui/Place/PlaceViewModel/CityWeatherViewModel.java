package com.example.weather.Ui.Place.PlaceViewModel;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.Logic.WeatherDataInquireTool;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.AdviseData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.AdviseDataUtility;
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

    //检测近7天的天气变化情况
    private MutableLiveData<List<SevenDayWeatherData.DailyDTO>> sevenDayWeatherLiveData = new MutableLiveData<>();
    private MutableLiveData<List<HourlyWeatherData.HourlyDTO>> hourWeatherLiveData = new MutableLiveData<>();

    private MutableLiveData<List<AdviseData.DailyDTO>> adviseWeatherLiveData = new MutableLiveData<>();


    public void setRequireActivity(Activity activity) {
        this.requireActivity = activity;
    }


    /**
     * 获取当前24小时的天气情况
     * @return 返回保存天气情况的链表
     */
    public MutableLiveData<List<HourlyWeatherData.HourlyDTO>> getHourlyDTo(String Location) {


        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/weather/24h?location=" + Location + "&key=64f323b501dc410cb7ec4fd1b503aab4";


        HourlyWeatherUtility utility = new HourlyWeatherUtility();

        utility.sendAddress(address, new DataCallback<HourlyWeatherData.HourlyDTO>() {

            @Override
            public void onSuccess(List<HourlyWeatherData.HourlyDTO> dataList) {
                WeatherDataInquireTool.HourSplitList(dataList);
                hourWeatherLiveData.postValue(dataList);
            }

            @Override
            public void onFailure(IOException e) {
                hourWeatherLiveData.postValue(WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao().getAllData());
            }
        });

        return hourWeatherLiveData;
    }

    /**
     * 获取7日内的天气情况
     * @param Location 地址
     */
    public MutableLiveData<List<SevenDayWeatherData.DailyDTO>> getSevenDayWeather(String Location) {
        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/weather/7d?location=" + Location + "&key=64f323b501dc410cb7ec4fd1b503aab4";

        SevenDayWeatherUtility utility = new SevenDayWeatherUtility();

        utility.sendAddress(address, new DataCallback<SevenDayWeatherData.DailyDTO>() {

            @Override
            public void onSuccess(List<SevenDayWeatherData.DailyDTO> dataList) {

                WeatherDataInquireTool.DaySplitList(dataList);
                sevenDayWeatherLiveData.postValue(dataList);
            }

            @Override
            public void onFailure(IOException e) {
                sevenDayWeatherLiveData.postValue(WeatherDataInquireTool.dpDayWeatherDatabase.weatherDataDao().getAllData());
            }
        });
        return sevenDayWeatherLiveData;
    }

    public MutableLiveData<List<AdviseData.DailyDTO>> getAdviseWeatherLiveData(String Location){
        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/indices/1d?type=1,2,3,4,6,7,8,9,5&location="+ Location +"&key=64f323b501dc410cb7ec4fd1b503aab4";

        AdviseDataUtility utility = new AdviseDataUtility();

        utility.sendAddress(address, new DataCallback<AdviseData.DailyDTO>() {

            @Override
            public void onSuccess(List<AdviseData.DailyDTO> dataList) {

                WeatherDataInquireTool.AdviseSplitList(dataList);
                adviseWeatherLiveData.postValue(dataList);
            }

            @Override
            public void onFailure(IOException e) {
                adviseWeatherLiveData.postValue(WeatherDataInquireTool.dpAdviseDatabase.WeatherDataDao().getAllData());
            }
        });
        return adviseWeatherLiveData;
    }
}
