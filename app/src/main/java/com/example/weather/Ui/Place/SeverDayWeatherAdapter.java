package com.example.weather.Ui.Place;

import static com.example.weather.Ui.Place.SeverDayWeatherAdapter.*;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;
import com.example.weather.R;
import com.example.weather.databinding.SeverDataWeatherItemBinding;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名: weather
 * 文件名: SeverDayWeatherAdapter
 * 创建者: lukecc0
 * 创建时间:2023/10/15 下午9:40
 * 描述: TODO
 */

public class SeverDayWeatherAdapter extends RecyclerView.Adapter<SeverDayWeatherAdapter.ViewHolder> {

    private List<SevenDayWeatherData.DailyDTO> mWeatherData;

    public SeverDayWeatherAdapter(List<SevenDayWeatherData.DailyDTO> mWeatherData) {
        this.mWeatherData = mWeatherData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sever_data_weather_item, parent, false);

        final SeverDayWeatherAdapter.ViewHolder holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SevenDayWeatherData.DailyDTO dailyDTO = mWeatherData.get(position);

        //解析日期并且设置
        String input = dailyDTO.getFxDate();
        Pattern pattern = Pattern.compile("(\\d{2})-(\\d{2})-(\\d{2})");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String month = matcher.group(2); // 月份
            String day = matcher.group(3);   // 日期

            // 设置提取的月份和日期到 holder.binding.day
            holder.binding.day.setText(month + " / " + day);
        }

        //设置图片


        //设置温度
        holder.binding.dayTemperature.setText(dailyDTO.getTempMin() + "° / " + dailyDTO.getTempMax() + "°");

    }

    @Override
    public int getItemCount() {
        return mWeatherData != null ? mWeatherData.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        SeverDataWeatherItemBinding binding;

        View WeatherView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SeverDataWeatherItemBinding.bind(itemView);
            WeatherView = itemView;
        }
    }
}
