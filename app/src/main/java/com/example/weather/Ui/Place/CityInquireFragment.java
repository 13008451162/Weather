package com.example.weather.Ui.Place;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weather.Logic.netWorkUtil.LocationData;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.Ui.Place.PlaceViewModel.LocationDataCallback;
import com.example.weather.Logic.netWorkUtil.LocationUtility;
import com.example.weather.Logic.netWorkUtil.PopularCitiesData;
import com.example.weather.Ui.Place.PlaceViewModel.PopularCitiesDataCallback;
import com.example.weather.Logic.netWorkUtil.PopularCitiesUtility;
import com.example.weather.WeatherApplication;
import com.example.weather.databinding.FragmentPlaceBinding;

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

    //制作文本监听器，监听文本变化情况
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlaceBinding.inflate(inflater, container, false);
        //显示热门城市数据
        PopularCities();
        binding.placeCityText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //显示搜索的城市数据
                LocationListDisplay(charSequence.toString());
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

                if(inputText.length() != 0){
                    //显示搜索的城市数据
                    LocationListDisplay(inputText);
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //释放绑定类，防止内存泄露
        binding = null;
    }

    /**
     * 用于显示当前15个热门城市数据
     */
    public void PopularCities() {
        //获取热门数据地址
        String address = "https://geoapi.qweather.com/v2/city/top?number=15&range=cn&key=64f323b501dc410cb7ec4fd1b503aab4";
        PopularCitiesUtility.SendAddress(address, new PopularCitiesDataCallback() {

            @Override
            public void onSuccess(List<PopularCitiesData.TopCityListDTO> popularCitiesList) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.NoNet.setVisibility(View.GONE);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(WeatherApplication.getContext(), 4);
                        binding.PopularRecyclerViewCity.setLayoutManager(gridLayoutManager);
                        PopularCitiesAdapter adapter = new PopularCitiesAdapter(popularCitiesList);
                        binding.PopularRecyclerViewCity.setAdapter(adapter);

                        //刷新数据
                        if (popularCitiesList != null) {
                            adapter.notifyItemInserted(popularCitiesList.size() - 1);
                        }

                        //城市搜索不可见，热门城市可见
                        binding.recyclerViewCity.setVisibility(View.GONE);
                        binding.PopularRecyclerViewCity.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onFailure(IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherApplication.getContext(), "打开”移动数据“或\nWLAN网络来访问", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 用于返回搜索的位置信息
     * @param text 模糊搜索的地址内容
     */
    public void LocationListDisplay(String text) {

        boolean Visibility = true;

        //当开始输入文本时，热门城市推荐和搜索不到的试图被隐藏
        binding.NoNet.setVisibility(View.GONE);
        binding.PopularRecyclerViewCity.setVisibility(View.GONE);

        //线程阻塞0.03s防止用户输入数据过快导致但网络较差导致试图重叠显示
        try {
            Thread.currentThread().join(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //判断当前输入框是否有数据,如果有数据的话显示热门城市数据
        if (text.length() == 0) {
            LogUtil.v("ViewNO", "成功");
            //显示热门城市数据
            PopularCities();
        } else {
            //访问的地址
            String addressSum = "https://geoapi.qweather.com/v2/city/lookup?location=" + text + "&key=64f323b501dc410cb7ec4fd1b503aab4";

            /**
             * 从 Java 8 版本开始，局部变量可以不显式声明为 final，
             * 但仍然需要满足 "effectively final" 的条件。
             * "Effectively final" 意味着变量在分配初值后不再被修改。
             */
            boolean finalVisibility = Visibility;
            LocationUtility.SendAddress(addressSum, new LocationDataCallback() {
                @Override
                public void onSuccess(List<LocationData.LocationDTO> locationList) {
                    //回调成功以后在Ui线程中更新RecyclerView
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WeatherApplication.getContext());
                            binding.recyclerViewCity.setLayoutManager(linearLayoutManager);
                            PlaceAdapter adapter = new PlaceAdapter(locationList);
                            binding.recyclerViewCity.setAdapter(adapter);

                            //刷新数据
                            if (locationList != null) {
                                adapter.notifyItemInserted(locationList.size() - 1);
                            } else {
                                //搜索不到时显示的视图
                                binding.NoNet.setVisibility(View.VISIBLE);

                            }
                            //热门城市不可见，城市搜索可见
                            binding.PopularRecyclerViewCity.setVisibility(View.GONE);
                            binding.recyclerViewCity.setVisibility(View.VISIBLE);
                        }
                    });
                }

                @Override
                public void onFailure(IOException e) {
                    e.printStackTrace();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WeatherApplication.getContext(), "打开”移动数据“或\nWLAN网络来访问", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
