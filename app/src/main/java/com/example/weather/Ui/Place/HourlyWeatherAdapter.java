package com.example.weather.Ui.Place;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Logic.netWorkUtil.HourlyWeatherData;
import com.example.weather.R;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.databinding.TodayWeatherItemBinding;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名: weather
 * 文件名: HourlyWeatherAdapter
 * 创建者: lukecc0
 * 创建时间:2023/10/7 下午9:53
 * 描述: 用于显示24小时天气的适配器
 */

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.ViewHelder> {


    private List<HourlyWeatherData.HourlyDTO> mWeatherData;

    public HourlyWeatherAdapter(List<HourlyWeatherData.HourlyDTO> mWeatherData) {
        this.mWeatherData = mWeatherData;
    }

    @NonNull
    @Override
    public ViewHelder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_weather_item, parent, false);

        ViewHelder helder = new ViewHelder(view);

        return helder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHelder holder, int position) {
        HourlyWeatherData.HourlyDTO hourlyDTO = mWeatherData.get(position);


        //解析字符串得到时间
        String input = hourlyDTO.getFxTime();
        Pattern pattern = Pattern.compile("T(\\d{2}:\\d{2})");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String time = matcher.group(1);
            LogUtil.d("USSA", time);
            holder.binding.hour.setText(time);
        }
        //设置图片
//        holder.binding.img.setImageResource();

        holder.binding.hourTemperature.setText(hourlyDTO.getTemp() + "°");

    }

    @Override
    public int getItemCount() {

        return mWeatherData != null ? mWeatherData.size() : 0;
    }

    static class ViewHelder extends RecyclerView.ViewHolder {

        TodayWeatherItemBinding binding;
        View WeatherView;

        public ViewHelder(@NonNull View itemView) {
            super(itemView);
            binding = TodayWeatherItemBinding.bind(itemView);
            WeatherView = itemView;
        }
    }

}
