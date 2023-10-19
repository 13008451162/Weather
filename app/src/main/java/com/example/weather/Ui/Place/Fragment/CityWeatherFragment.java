package com.example.weather.Ui.Place.Fragment;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.weather.LocationServicesDome.MyLocationListener;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;
import com.example.weather.Ui.Place.HourlyWeatherAdapter;
import com.example.weather.Ui.Place.PlaceViewModel.CityWeatherViewModel;
import com.example.weather.Ui.Place.SeverDayWeatherAdapter;
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

        //设置提示框文本的点击事件，实现点击缩放文本框大小使得文本完全显示和部分显示
        binding.NowAdvise.setOnClickListener(new View.OnClickListener() {
            private boolean isExpanded = false; // 初始状态为未展开

            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;

                if (isExpanded) {
                    // 如果已展开，还原到初始状态
                    textView.setMaxLines(1); // 设置最大行数为1
                    textView.setEllipsize(TextUtils.TruncateAt.END); // 添加省略号
                    textView.setSingleLine(true); // 设置为单行模式
                    isExpanded = false; // 更新状态
                } else {
                    // 如果未展开，展开文本
                    textView.setMaxLines(Integer.MAX_VALUE); // 移除最大行数限制
                    textView.setEllipsize(null); // 移除省略号
                    textView.setSingleLine(false); // 设置为多行模式
                    isExpanded = true; // 更新状态
                }
            }
        });


        return binding.getRoot();
    }


    /**
     * 显示当前位置页面的信息
     */
    private void currentLocation() {
        //获取当前位置信息,加载当前位置信息的数据
        MyLocationListener myLocationListener = MyLocationListener.getInstance();
        myLocationListener.bdLocationMutableLiveData.observe(requireActivity(), location -> {

            String locationInformation = String.format("%.2f", location.getLongitude()) + "," + String.format("%.2f", location.getLatitude());
            updateHourWeather(locationInformation);
            updateDayWeather(locationInformation);
            upDataAdvise(locationInformation, location.getDistrict());
        });
    }

    /**
     * 加载指定位置的信息
     *
     * @param id
     */
    private void specifyLocation(String id, String locationName) {
        updateDayWeather(id);
        updateHourWeather(id);
        upDataAdvise(id, locationName);
    }


    /**
     * 用于显示24小时天气
     *
     * @param locationInformation 位置信息
     */
    private void updateHourWeather(String locationInformation) {

        viewModel.getHourlyDTo(locationInformation).observe(requireActivity(), dailyDTOS -> {

            //设置主界面当前的温度
            binding.NowTemp.setText(dailyDTOS.get(0).getTemp() + "°");

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
     *
     * @param locationInformation 位置信息
     */
    private void updateDayWeather(String locationInformation) {
        //加载视图数据
        viewModel.getSevenDayWeather(locationInformation).observe(requireActivity(), dailyDTOS -> {

            // 设置单位的字体大小
            int unitTextSize = 12; // 以 sp 为单位，根据需求调整

            SevenDayWeatherData.DailyDTO dto = dailyDTOS.get(0);

            String strAdvise = adviseUv(Integer.valueOf(dto.getUvIndex()));

            //设置主界面的温度范围和建议情况
            String nowTempAndsugg = dto.getTextDay() + "  " + dto.getTempMin() + "°/" + dto.getTempMax() + "°   " + strAdvise + "  " + dto.getUvIndex() + " mW/㎡";
            SpannableString spannableString = new SpannableString(nowTempAndsugg);
            spannableString.setSpan(new AbsoluteSizeSpan(unitTextSize, true), nowTempAndsugg.indexOf("mW"), nowTempAndsugg.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            binding.NowTempAndsugg.setText(spannableString);


            //设置主界面紫外线，能见度，相对湿度，云量，大气压强等

            // 原始文本内容
            String uvIndex = dto.getUvIndex() + " mW/㎡";
            String humidity = dto.getHumidity() + " %rh";
            String precipitation = dto.getPrecip() + " mm";
            String pressure = dto.getPressure() + " Hpa";
            String Visbility = dto.getVis() + " km";


            // 创建 SpannableString 对象
            SpannableString spannableUvIndex = new SpannableString(uvIndex);
            SpannableString spannableHumidity = new SpannableString(humidity);
            SpannableString spannablePrecipitation = new SpannableString(precipitation);
            SpannableString spannablePressure = new SpannableString(pressure);
            SpannableString spannableVisbility = new SpannableString(Visbility);

            //设置需要修改字体的文本内容
            spannableUvIndex.setSpan(new AbsoluteSizeSpan(unitTextSize, true), uvIndex.indexOf("mW"), uvIndex.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableHumidity.setSpan(new AbsoluteSizeSpan(unitTextSize, true), humidity.indexOf("%"), humidity.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannablePrecipitation.setSpan(new AbsoluteSizeSpan(unitTextSize, true), precipitation.indexOf("m"), precipitation.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannablePressure.setSpan(new AbsoluteSizeSpan(unitTextSize, true), pressure.indexOf("H"), pressure.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableVisbility.setSpan(new AbsoluteSizeSpan(unitTextSize, true), Visbility.indexOf("k"), Visbility.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // 设置 TextView 文本
            binding.GridUv.setText(spannableUvIndex);
            binding.GridCloud.setText(dto.getCloud());
            binding.GridHumidity.setText(spannableHumidity);

            binding.GridPrecipitation.setText(spannablePrecipitation);
            binding.GridPressure.setText(spannablePressure);
            binding.GridVisbility.setText(spannableVisbility);


            //设置7日内天气情况的RecyclerView
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            binding.futureWeater.setLayoutManager(linearLayoutManager);

            SeverDayWeatherAdapter adapter = new SeverDayWeatherAdapter(dailyDTOS);
            binding.futureWeater.setAdapter(adapter);
        });
    }

    /**
     * 用于显示当天的建议
     *
     * @param locationInformation 地址Id/经纬度
     * @param locationName        位置的名称
     */
    private void upDataAdvise(String locationInformation, String locationName) {

        viewModel.getAdviseWeatherLiveData(locationInformation, locationName).observe(requireActivity(), dailyDTOS -> {

            binding.Sports.setText(dailyDTOS.get(0).getCategory() + "运动");
            binding.CarWash.setText(dailyDTOS.get(1).getCategory() + "洗车");
            binding.Dressing.setText("穿衣" + dailyDTOS.get(2).getCategory());
            binding.Fishing.setText(dailyDTOS.get(3).getCategory() + "钓鱼");
            binding.NowAdvise.setText(dailyDTOS.get(4).getText());
            binding.Travel.setText(dailyDTOS.get(5).getCategory() + "旅游");
            binding.Allergy.setText(dailyDTOS.get(6).getCategory() + "过敏");
            binding.Comfortable.setText(dailyDTOS.get(7).getCategory());
            binding.Cold.setText(dailyDTOS.get(8).getCategory() + "感冒");
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    /**
     * 根据紫外线情况获取相关建议
     *
     * @param integer 紫外线数值
     * @return 获得的建议String
     */
    private String adviseUv(Integer integer) {

        String strAdvise;

        if (integer > 0 && integer <= 2) {
            strAdvise = "紫外线最弱";
        } else if (integer > 2 && integer <= 4) {
            strAdvise = "紫外线较弱";
        } else if (integer > 4 && integer <= 6) {
            strAdvise = "紫外线中等";
        } else if (integer > 6 && integer <= 9) {
            strAdvise = "紫外线较强";
        } else {
            strAdvise = "紫外线有害";
        }
        return strAdvise;
    }

}
