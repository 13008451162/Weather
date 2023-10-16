package com.example.weather.Ui.Place.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weather.LocationServicesDome.MyLocationListener;
import com.example.weather.Logic.WeatherDataInquireTool;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.Ui.Place.HourlyWeatherAdapter;
import com.example.weather.Ui.Place.PlaceViewModel.CityWeatherViewModel;
import com.example.weather.Ui.Place.PlaceViewModel.DataCallback;
import com.example.weather.Ui.Place.SeverDayWeatherAdapter;
import com.example.weather.databinding.CityWeaterFragmentBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名: weather
 * 文件名: CityWeatherFragment
 * 创建者: lukecc0
 * 创建时间:2023/9/27 下午10:01
 * 描述: TODO
 */

public class CityWeatherFragment extends Fragment {

    private static final String TAG = "haojinhui";

    private CityWeaterFragmentBinding binding;
    private CityWeatherViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = CityWeaterFragmentBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(CityWeatherViewModel.class);

        viewModel.setRequireActivity(requireActivity());

        /*
        这里有一个if语句判断当前位置是初始位置还是被加载位置
         */
        currentLocation();


        return binding.getRoot();
    }



    /**
     * 显示当前位置页面的信息
     */
    public void currentLocation() {
        //获取当前位置信息,加载当前位置信息的数据
        MyLocationListener myLocationListener = MyLocationListener.getInstance();
        myLocationListener.locationInformationLiveData.observe(requireActivity(), this::updateHourWeather);
        myLocationListener.locationInformationLiveData.observe(requireActivity(),this::updateDayWeather);


    }


    /**
     * 用于显示24小时天气
     * @param locationInformation 位置信息
     */
    private void updateHourWeather(String locationInformation) {
        viewModel.getHourlyDTo(locationInformation).observe(requireActivity(), dailyDTOS -> {
            //制作横向的布局方式
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            binding.TodayWeather.setLayoutManager(linearLayoutManager);


            HourlyWeatherAdapter adapter = new HourlyWeatherAdapter(dailyDTOS);
            binding.TodayWeather.setAdapter(adapter);
        });
    }

    /**
     * 用于显示7日内的天气信息
     * @param locationInformation 位置信息
     */
    private void updateDayWeather(String locationInformation){
        //加载视图数据
        viewModel.getSevenDayWeather(locationInformation).observe(requireActivity(), dailyDTOS -> {

            getActivity().runOnUiThread(() -> {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                binding.futureWeater.setLayoutManager(linearLayoutManager);

                SeverDayWeatherAdapter adapter = new SeverDayWeatherAdapter(dailyDTOS);
                binding.futureWeater.setAdapter(adapter);
            });

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
