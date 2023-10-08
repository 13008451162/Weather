package com.example.weather.Ui.Place.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
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

import com.baidu.mshield.ac.F;
import com.example.weather.LocationServicesDome.MyLocationListener;
import com.example.weather.Logic.netWorkUtil.HourlyWeatherData;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.Ui.Place.HourlyWeatherAdapter;
import com.example.weather.Ui.Place.PlaceViewModel.CityWeatherViewModel;
import com.example.weather.Ui.Place.PlaceViewModel.DataCallback;
import com.example.weather.databinding.CityWeaterFragmentBinding;

import java.io.IOException;
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

        viewModel.setActivity(getActivity());

        //获取当前位置信息
        MyLocationListener myLocationListener = MyLocationListener.getInstance();

//         String LocationInformation = myLocationListener.getLocationInformation();
        myLocationListener.locationInformationLiveData.observe(requireActivity(), this::update);

//        LogUtil.d("YSSY",LocationInformation);
        //显示24小时天气


        return binding.getRoot();
    }

    private void update(String locationInformation) {
        viewModel.getHourlyDTo(locationInformation, new DataCallback<HourlyWeatherData.HourlyDTO>() {
            @Override
            public void onSuccess(List<HourlyWeatherData.HourlyDTO> dataList) {
                getActivity().runOnUiThread(() -> {

                    //制作横向的布局方式
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    binding.TodayWeather.setLayoutManager(linearLayoutManager);

                    HourlyWeatherAdapter adapter = new HourlyWeatherAdapter(dataList);
                    binding.TodayWeather.setAdapter(adapter);
                });
            }

            @Override
            public void onFailure(IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "定位失败,请检查定位是否开启", Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
