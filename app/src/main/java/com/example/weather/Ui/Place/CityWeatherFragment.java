package com.example.weather.Ui.Place;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baidu.mshield.ac.F;
import com.example.weather.databinding.CityWeaterFragmentBinding;

/**
 * 项目名: weather
 * 文件名: CityWeatherFragment
 * 创建者: lukecc0
 * 创建时间:2023/9/27 下午10:01
 * 描述: TODO
 */

public class CityWeatherFragment extends Fragment {

    private CityWeaterFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = CityWeaterFragmentBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
