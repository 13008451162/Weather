package com.example.weather.Ui.Place.PlaceViewModel;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.weather.Logic.netWorkUtil.HourlyWeatherData;
import com.example.weather.Logic.netWorkUtil.HourlyWeatherUtility;

import java.io.IOException;
import java.util.List;

import javax.security.auth.callback.Callback;

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
    public void getHourlyDTo(String Location, DataCallback<HourlyWeatherData.HourlyDTO> callback) {

        List<HourlyWeatherData.HourlyDTO> mList;

        //获取天气的地址
        String address = "https://devapi.qweather.com/v7/weather/24h?location=" + Location + "&key=64f323b501dc410cb7ec4fd1b503aab4";

        Log.e("haojinhui", "getHourlyDTo: " + address);

        HourlyWeatherUtility utility = new HourlyWeatherUtility();

        utility.sendAddress(address, new DataCallback<HourlyWeatherData.HourlyDTO>() {

            @Override
            public void onSuccess(List<HourlyWeatherData.HourlyDTO> dataList) {
                callback.onSuccess(dataList);
            }

            @Override
            public void onFailure(IOException e) {
                callback.onFailure(e);
            }
        });
    }
}
