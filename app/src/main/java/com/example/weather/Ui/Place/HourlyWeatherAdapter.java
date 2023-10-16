package com.example.weather.Ui.Place;

import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
import com.example.weather.MainActivity;
import com.example.weather.R;
import com.example.weather.TestTool.LogUtil;
import com.example.weather.databinding.TodayWeatherItemBinding;

import java.util.ArrayList;
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

        //添加一个当前元素
        List<HourlyWeatherData.HourlyDTO> data = new ArrayList<>();

        HourlyWeatherData.HourlyDTO dto = new HourlyWeatherData.HourlyDTO(mWeatherData.get(0));
        dto.setFxTime("现在");
        data.add(dto);

        data.addAll(mWeatherData);

        this.mWeatherData = data;
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
            holder.binding.hour.setText(time);
        } else {
            //fxTime为现在的天气
            holder.binding.hour.setText(hourlyDTO.getFxTime());
        }

        //设置图片
        holder.binding.img.setTypeface(MainActivity.getFont());//设置img使用图标字体。
        SettingsIcon(holder.binding.img,hourlyDTO.getIcon());

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

    /**
     * 用于获取和风天气而气象图标
     * @param textView 需要修改的视图
     * @param iconday 被添加的图标代码
     */
    private void SettingsIcon(TextView textView, String iconday) {
        if (iconday.equals("100")){
            textView.setText(Html.fromHtml("&#xF101;"));
        }else if(iconday.equals("101")){
            textView.setText(Html.fromHtml("&#xF102;"));
        }else if(iconday.equals("102")) {
            textView.setText(Html.fromHtml("&#xF103;"));
        }else if(iconday.equals("103")) {
            textView.setText(Html.fromHtml("&#xF104;"));
        }else if(iconday.equals("104")) {
            textView.setText(Html.fromHtml("&#xF105;"));
        }else if(iconday.equals("150")) {
            textView.setText(Html.fromHtml("&#xF106;"));
        }else if(iconday.equals("151")) {
            textView.setText(Html.fromHtml("&#xF107;"));
        }else if(iconday.equals("152")) {
            textView.setText(Html.fromHtml("&#xF108;"));
        }else if(iconday.equals("153")) {
            textView.setText(Html.fromHtml("&#xF109;"));
        } else if(iconday.equals("300")) {
            textView.setText(Html.fromHtml("&#xF10A;"));
        }else if(iconday.equals("301")) {
            textView.setText(Html.fromHtml("&#xF10B;"));
        }else if(iconday.equals("302")) {
            textView.setText(Html.fromHtml("&#xF10C;"));
        }else if(iconday.equals("303")) {
            textView.setText(Html.fromHtml("&#xF10D;"));
        }else if(iconday.equals("304")) {
            textView.setText(Html.fromHtml("&#xF10E;"));
        }else if(iconday.equals("305")) {
            textView.setText(Html.fromHtml("&#xF10F;"));
        }else if(iconday.equals("306")) {
            textView.setText(Html.fromHtml("&#xF110;"));
        }else if(iconday.equals("307")) {
            textView.setText(Html.fromHtml("&#xF111;"));
        }else if(iconday.equals("308")) {
            textView.setText(Html.fromHtml("&#xF112;"));
        }else if(iconday.equals("309")) {
            textView.setText(Html.fromHtml("&#xF113;"));
        }else if(iconday.equals("310")) {
            textView.setText(Html.fromHtml("&#xF114;"));
        }else if(iconday.equals("311")) {
            textView.setText(Html.fromHtml("&#xF115;"));
        }else if(iconday.equals("312")) {
            textView.setText(Html.fromHtml("&#xF116;"));
        }else if(iconday.equals("313")) {
            textView.setText(Html.fromHtml("&#xF117;"));
        }else if(iconday.equals("314")) {
            textView.setText(Html.fromHtml("&#xF118;"));
        }else if(iconday.equals("315")) {
            textView.setText(Html.fromHtml("&#xF119;"));
        }else if(iconday.equals("316")) {
            textView.setText(Html.fromHtml("&#xF11A;"));
        }else if(iconday.equals("317")) {
            textView.setText(Html.fromHtml("&#xF11B;"));
        }else if(iconday.equals("318")) {
            textView.setText(Html.fromHtml("&#xF11C;"));
        }else if(iconday.equals("399")) {
            textView.setText(Html.fromHtml("&#xF11F;"));
        }else if(iconday.equals("350")) {
            textView.setText(Html.fromHtml("&#xF11D;"));
        }else if(iconday.equals("351")) {
            textView.setText(Html.fromHtml("&#xF11E;"));
        }else if(iconday.equals("400")) {
            textView.setText(Html.fromHtml("&#xF120;"));
        }else if(iconday.equals("401")) {
            textView.setText(Html.fromHtml("&#xF121;"));
        }else if(iconday.equals("402")) {
            textView.setText(Html.fromHtml("&#xF122;"));
        }else if(iconday.equals("403")) {
            textView.setText(Html.fromHtml("&#xF123;"));
        }else if(iconday.equals("404")) {
            textView.setText(Html.fromHtml("&#xF124;"));
        }else if(iconday.equals("405")) {
            textView.setText(Html.fromHtml("&#xF125;"));
        }else if(iconday.equals("406")) {
            textView.setText(Html.fromHtml("&#xF126;"));
        }else if(iconday.equals("407")) {
            textView.setText(Html.fromHtml("&#xF127;"));
        }else if(iconday.equals("408")) {
            textView.setText(Html.fromHtml("&#xF128;"));
        }else if(iconday.equals("409")) {
            textView.setText(Html.fromHtml("&#xF129;"));
        }else if(iconday.equals("410")) {
            textView.setText(Html.fromHtml("&#xF12A;"));
        }else if(iconday.equals("456")) {
            textView.setText(Html.fromHtml("&#xF12B;"));
        }else if(iconday.equals("457")) {
            textView.setText(Html.fromHtml("&#xF12C;"));
        }else if(iconday.equals("499")) {
            textView.setText(Html.fromHtml("&#xF12D;"));
        }else if(iconday.equals("500")) {
            textView.setText(Html.fromHtml("&#xF12E;"));
        }else if(iconday.equals("501")) {
            textView.setText(Html.fromHtml("&#xF12F;"));
        }else if(iconday.equals("502")) {
            textView.setText(Html.fromHtml("&#xF130;"));
        }else if(iconday.equals("503")) {
            textView.setText(Html.fromHtml("&#xF131;"));
        }else if(iconday.equals("504")) {
            textView.setText(Html.fromHtml("&#xF132;"));
        }else if(iconday.equals("507")) {
            textView.setText(Html.fromHtml("&#xF133;"));
        }else if(iconday.equals("508")) {
            textView.setText(Html.fromHtml("&#xF134;"));
        }else if(iconday.equals("509")) {
            textView.setText(Html.fromHtml("&#xF135;"));
        }else if(iconday.equals("510")) {
            textView.setText(Html.fromHtml("&#xF136;"));
        }else if(iconday.equals("511")) {
            textView.setText(Html.fromHtml("&#xF137;"));
        }else if(iconday.equals("512")) {
            textView.setText(Html.fromHtml("&#xF138;"));
        }else if(iconday.equals("513")) {
            textView.setText(Html.fromHtml("&#xF139;"));
        }else if(iconday.equals("514")) {
            textView.setText(Html.fromHtml("&#xF13A;"));
        }else if(iconday.equals("515")) {
            textView.setText(Html.fromHtml("&#xF13B;"));
        }else if(iconday.equals("900")) {
            textView.setText(Html.fromHtml("&#xF144;"));
        }else if(iconday.equals("901")) {
            textView.setText(Html.fromHtml("&#xF145;"));
        }else{
            textView.setText(Html.fromHtml("&#xF146;"));
        }
    }

}
