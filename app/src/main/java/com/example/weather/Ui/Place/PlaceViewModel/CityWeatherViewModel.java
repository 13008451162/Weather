package com.example.weather.Ui.Place.PlaceViewModel;

import android.app.Activity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weather.Logic.WeatherDataInquireTool;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.AdviseData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.AdviseDataUtility;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherUtility;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherUtility;
import com.example.weather.MainActivity;

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
     * 请求24小时内的天气情况
     *
     * @param Location 地址Id/经纬度
     * @return 返回保存天气情况链表的Livedata
     */
    public MutableLiveData<List<HourlyWeatherData.HourlyDTO>> getHourlyDTo(final String Location) {


        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/weather/24h?location=" + Location + "&key=64f323b501dc410cb7ec4fd1b503aab4";


        HourlyWeatherUtility utility = new HourlyWeatherUtility();

        utility.sendAddress(address, new DataCallback<HourlyWeatherData.HourlyDTO>() {

            @Override
            public void onSuccess(List<HourlyWeatherData.HourlyDTO> dataList) {
                WeatherDataInquireTool.HourSplitList(dataList, Location);
                hourWeatherLiveData.postValue(dataList);
            }

            @Override
            public void onFailure(IOException e) {
                hourWeatherLiveData.postValue(WeatherDataInquireTool.dpHourWeatherDatabase.weatherDataDao().getDataByLocation(Location));
            }
        });

        return hourWeatherLiveData;
    }


    /**
     * 获取7日内的天气情况
     *
     * @param Location 地址Id/经纬度
     * @return 返回保存7日天气情况的LiveData
     */
    public MutableLiveData<List<SevenDayWeatherData.DailyDTO>> getSevenDayWeather(final String Location) {
        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/weather/7d?location=" + Location + "&key=64f323b501dc410cb7ec4fd1b503aab4";

        SevenDayWeatherUtility utility = new SevenDayWeatherUtility();

        utility.sendAddress(address, new DataCallback<SevenDayWeatherData.DailyDTO>() {

            @Override
            public void onSuccess(List<SevenDayWeatherData.DailyDTO> dataList) {

                WeatherDataInquireTool.DaySplitList(dataList, Location);
                sevenDayWeatherLiveData.postValue(dataList);
            }

            @Override
            public void onFailure(IOException e) {
                sevenDayWeatherLiveData.postValue(WeatherDataInquireTool.dpDayWeatherDatabase.weatherDataDao().getDataByLocation(Location));
            }
        });
        return sevenDayWeatherLiveData;
    }

    /**
     * 请求当日天气情况的生活建议
     *
     * @param Location     地址Id/经纬度
     * @param locationName 位置的名称
     * @return 返回保存当日天气情况的生活建议的LiveData
     */
    public MutableLiveData<List<AdviseData.DailyDTO>> getAdviseWeatherLiveData(final String Location, String locationName) {
        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/indices/1d?type=1,2,3,4,6,7,8,9,5&location=" + Location + "&key=64f323b501dc410cb7ec4fd1b503aab4";

        AdviseDataUtility utility = new AdviseDataUtility();


        utility.sendAddress(address, new DataCallback<AdviseData.DailyDTO>() {

            @Override
            public void onSuccess(List<AdviseData.DailyDTO> dataList) {

                WeatherDataInquireTool.AdviseSplitList(dataList, Location, locationName);
                adviseWeatherLiveData.postValue(dataList);

                //危险操作
                String Name = WeatherDataInquireTool.dpAdviseDatabase.WeatherDataDao()
                        .getDistrictByLocationId(Location);

                MainActivity.getActivity().runOnUiThread(() -> {
                    //危险的操作，在ViewModel中设置了主界面的名称
                    MainActivity.getBinding().LocationName.setText(Name);
                });
            }

            @Override
            public void onFailure(IOException e) {

                //危险操作
                String Name = WeatherDataInquireTool.dpAdviseDatabase.WeatherDataDao()
                        .getDistrictByLocationId(Location);

                MainActivity.getActivity().runOnUiThread(() -> {
                    //危险的操作，在ViewModel中设置了主界面的名称
                    MainActivity.getBinding().LocationName.setText(Name);
                });

                adviseWeatherLiveData.postValue(WeatherDataInquireTool.dpAdviseDatabase.WeatherDataDao().getDataByLocation(Location));
            }
        });
        return adviseWeatherLiveData;
    }
}
