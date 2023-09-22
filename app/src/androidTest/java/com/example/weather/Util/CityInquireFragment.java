package com.example.weather.Util;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weather.Logic.netWorkUtil.LocationData;
import com.example.weather.Logic.netWorkUtil.LocationDataCallback;
import com.example.weather.Logic.netWorkUtil.LocationUtility;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.Ui.Place.PlaceAdapter;
import com.example.weather.WeatherApplication;
import com.example.weather.databinding.FragmentPlaceBinding;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 项目名: weather
 * 文件名: CityInquireFragment
 * 创建者: lukecc0
 * 创建时间:2023/9/21 下午4:51
 * 描述: 搜索城市的Fragment页面
 */

public class CityInquireFragment extends Fragment {
    FragmentPlaceBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlaceBinding.inflate(inflater,container,false);

        String inputText = binding.placeCityText.getText().toString();

        binding.placeCityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String addressSum = "https://geoapi.qweather.com/v2/city/lookup?location=" + charSequence.toString() + "&key=64f323b501dc410cb7ec4fd1b503aab4";
                LocationUtility.SendAddress(addressSum, new LocationDataCallback() {
                    @Override
                    public void onSuccess(List<LocationData.LocationDTO> locationList) {
                        LogUtil.v("list",locationList.toString());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WeatherApplication.getContext());
                                binding.recyclerViewCity.setLayoutManager(linearLayoutManager);
                                PlaceAdapter adapter = new PlaceAdapter(locationList);
                                binding.recyclerViewCity.setAdapter(adapter);
                                //刷新数据
                                adapter.notifyItemInserted(locationList.size()-1);
                            }
                        });
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //搜索按钮
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = binding.placeCityText.getText().toString();

                String addressSum = "https://geoapi.qweather.com/v2/city/lookup?location=" + inputText + "&key=64f323b501dc410cb7ec4fd1b503aab4";
                LocationUtility.SendAddress(addressSum, new LocationDataCallback() {
                    @Override
                    public void onSuccess(List<LocationData.LocationDTO> locationList) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LogUtil.v("list",locationList.toString());
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WeatherApplication.getContext());
                                binding.recyclerViewCity.setLayoutManager(linearLayoutManager);
                                PlaceAdapter adapter = new PlaceAdapter(locationList);
                                binding.recyclerViewCity.setAdapter(adapter);
                                //刷新数据
                                adapter.notifyItemInserted(locationList.size()-1);
                            }
                        });
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                });
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Test
    public void Test(){
        String addressSum = "https://geoapi.qweather.com/v2/city/lookup?location=" + "汉中" + "&key=64f323b501dc410cb7ec4fd1b503aab4";
        LocationUtility.SendAddress(addressSum, new LocationDataCallback() {
            @Override
            public void onSuccess(List<LocationData.LocationDTO> locationList) {
                LogUtil.v("list",locationList.toString());
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WeatherApplication.getContext());
//                        binding.recyclerViewCity.setLayoutManager(linearLayoutManager);
//                        PlaceAdapter adapter = new PlaceAdapter(locationList);
//                        binding.recyclerViewCity.setAdapter(adapter);
//                        //刷新数据
//                        adapter.notifyItemInserted(locationList.size()-1);
//                    }
//                });
            }

            @Override
            public void onFailure(IOException e) {

            }
        });
    }
}
