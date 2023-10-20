package com.example.weather.Ui.Place.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.LocationServicesDome.MyLocationListener;

import com.example.weather.Logic.netWorkUtil.LocationAndCity.HourlyWeatherData;
import com.example.weather.Logic.netWorkUtil.LocationAndCity.SevenDayWeatherData;
import com.example.weather.MainActivity;
import com.example.weather.R;
import com.example.weather.Ui.Place.PlaceViewModel.CityWeatherViewModel;
import com.example.weather.databinding.CityWeaterFragmentBinding;
import com.example.weather.databinding.SeverDataWeatherItemBinding;
import com.example.weather.databinding.TodayWeatherItemBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 项目名: weather
 * 文件名: CityWeatherFragment
 * 创建者: lukecc0
 * 创建时间:2023/9/27 下午10:01
 * 描述: 加载城市天气情况
 */

public class CityWeatherFragment extends Fragment {

    private CityWeaterFragmentBinding binding;
    private CityWeatherViewModel viewModel;

    private String idLocationName;      //位置的名称

    private String locationInformation; //位置的经纬度或者代码id

    public CityWeatherFragment(String idLocationName, String locationInformation) {
        this.idLocationName = idLocationName;
        this.locationInformation = locationInformation;
    }

    public CityWeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化 ViewModel
        viewModel = new ViewModelProvider(this).get(CityWeatherViewModel.class);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = CityWeaterFragmentBinding.inflate(inflater, container, false);


        viewModel.setRequireActivity(requireActivity());


        binding.SwipeRefreshLayout.setOnRefreshListener(() -> {
            pullDownToRefresh();
        });

        /*
        这里有一个if语句判断当前位置是初始位置还是被加载位置
         */
        if (locationInformation == null) {
            currentLocation();
        } else {
            specifyLocation();
        }

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
     * 实现下拉刷新功能
     */
    private void pullDownToRefresh() {

        //判断网络情况
        if (!MainActivity.isNetworkConnected(MainActivity.getActivity())) {
            Toast.makeText(MainActivity.getActivity(), "网络异常刷新失败", Toast.LENGTH_SHORT).show();
        }

        if (locationInformation == null) {
            currentLocation();
        } else {
            specifyLocation();
        }

        Handler handler = new Handler();
        handler.postDelayed((Runnable) () -> {
            binding.SwipeRefreshLayout.setRefreshing(false);

        }, 300);

    }


    /**
     * 显示当前位置页面的信息
     */
    private void currentLocation() {
        //检查 Fragment 是否已附加到 Activity，然后再执行相应的操作。
        if (isAdded() && viewModel.getRequireActivity() != null) {
            //获取当前位置信息,加载当前位置信息的数据
            MyLocationListener myLocationListener = MyLocationListener.getInstance();
            myLocationListener.bdLocationMutableLiveData.observe(requireActivity(), location -> {

                String locationInformation = String.format("%.2f", location.getLongitude()) + "," + String.format("%.2f", location.getLatitude());
                updateHourWeather(locationInformation);
                updateDayWeather(locationInformation);
                upDataAdvise(locationInformation, location.getDistrict());
            });
        }
    }

    /**
     * 加载指定位置的信息
     */
    private void specifyLocation() {
        updateDayWeather(locationInformation);
        updateHourWeather(locationInformation);
        upDataAdvise(locationInformation, idLocationName);
    }


    /**
     * 用于显示24小时天气
     *
     * @param locationInformation 位置信息
     */
    private void updateHourWeather(String locationInformation) {

        //检查 Fragment 是否已附加到 Activity，然后再执行相应的操作。
        if (isAdded() && viewModel.getRequireActivity() != null) {

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


    }

    /**
     * 用于显示7日内的天气信息
     *
     * @param locationInformation 位置信息
     */
    private void updateDayWeather(String locationInformation) {

        //检查 Fragment 是否已附加到 Activity，然后再执行相应的操作。
        if (isAdded() && viewModel.getRequireActivity() != null) {


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

    }

    /**
     * 用于显示当天的建议
     *
     * @param locationInformation 地址Id/经纬度
     * @param locationName        位置的名称
     */
    private void upDataAdvise(String locationInformation, String locationName) {

        //检查 Fragment 是否已附加到 Activity，然后再执行相应的操作。
        if (isAdded() && viewModel.getRequireActivity() != null) {
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


    /**
     * 用于获取和风天气而气象图标
     *
     * @param textView 需要修改的视图
     * @param iconday  被添加的图标代码
     */
    private void SettingsIcon(TextView textView, String iconday) {
        if (iconday.equals("100")) {
            textView.setText(Html.fromHtml("&#xF101;"));
        } else if (iconday.equals("101")) {
            textView.setText(Html.fromHtml("&#xF102;"));
        } else if (iconday.equals("102")) {
            textView.setText(Html.fromHtml("&#xF103;"));
        } else if (iconday.equals("103")) {
            textView.setText(Html.fromHtml("&#xF104;"));
        } else if (iconday.equals("104")) {
            textView.setText(Html.fromHtml("&#xF105;"));
        } else if (iconday.equals("150")) {
            textView.setText(Html.fromHtml("&#xF106;"));
        } else if (iconday.equals("151")) {
            textView.setText(Html.fromHtml("&#xF107;"));
        } else if (iconday.equals("152")) {
            textView.setText(Html.fromHtml("&#xF108;"));
        } else if (iconday.equals("153")) {
            textView.setText(Html.fromHtml("&#xF109;"));
        } else if (iconday.equals("300")) {
            textView.setText(Html.fromHtml("&#xF10A;"));
        } else if (iconday.equals("301")) {
            textView.setText(Html.fromHtml("&#xF10B;"));
        } else if (iconday.equals("302")) {
            textView.setText(Html.fromHtml("&#xF10C;"));
        } else if (iconday.equals("303")) {
            textView.setText(Html.fromHtml("&#xF10D;"));
        } else if (iconday.equals("304")) {
            textView.setText(Html.fromHtml("&#xF10E;"));
        } else if (iconday.equals("305")) {
            textView.setText(Html.fromHtml("&#xF10F;"));
        } else if (iconday.equals("306")) {
            textView.setText(Html.fromHtml("&#xF110;"));
        } else if (iconday.equals("307")) {
            textView.setText(Html.fromHtml("&#xF111;"));
        } else if (iconday.equals("308")) {
            textView.setText(Html.fromHtml("&#xF112;"));
        } else if (iconday.equals("309")) {
            textView.setText(Html.fromHtml("&#xF113;"));
        } else if (iconday.equals("310")) {
            textView.setText(Html.fromHtml("&#xF114;"));
        } else if (iconday.equals("311")) {
            textView.setText(Html.fromHtml("&#xF115;"));
        } else if (iconday.equals("312")) {
            textView.setText(Html.fromHtml("&#xF116;"));
        } else if (iconday.equals("313")) {
            textView.setText(Html.fromHtml("&#xF117;"));
        } else if (iconday.equals("314")) {
            textView.setText(Html.fromHtml("&#xF118;"));
        } else if (iconday.equals("315")) {
            textView.setText(Html.fromHtml("&#xF119;"));
        } else if (iconday.equals("316")) {
            textView.setText(Html.fromHtml("&#xF11A;"));
        } else if (iconday.equals("317")) {
            textView.setText(Html.fromHtml("&#xF11B;"));
        } else if (iconday.equals("318")) {
            textView.setText(Html.fromHtml("&#xF11C;"));
        } else if (iconday.equals("399")) {
            textView.setText(Html.fromHtml("&#xF11F;"));
        } else if (iconday.equals("350")) {
            textView.setText(Html.fromHtml("&#xF11D;"));
        } else if (iconday.equals("351")) {
            textView.setText(Html.fromHtml("&#xF11E;"));
        } else if (iconday.equals("400")) {
            textView.setText(Html.fromHtml("&#xF120;"));
        } else if (iconday.equals("401")) {
            textView.setText(Html.fromHtml("&#xF121;"));
        } else if (iconday.equals("402")) {
            textView.setText(Html.fromHtml("&#xF122;"));
        } else if (iconday.equals("403")) {
            textView.setText(Html.fromHtml("&#xF123;"));
        } else if (iconday.equals("404")) {
            textView.setText(Html.fromHtml("&#xF124;"));
        } else if (iconday.equals("405")) {
            textView.setText(Html.fromHtml("&#xF125;"));
        } else if (iconday.equals("406")) {
            textView.setText(Html.fromHtml("&#xF126;"));
        } else if (iconday.equals("407")) {
            textView.setText(Html.fromHtml("&#xF127;"));
        } else if (iconday.equals("408")) {
            textView.setText(Html.fromHtml("&#xF128;"));
        } else if (iconday.equals("409")) {
            textView.setText(Html.fromHtml("&#xF129;"));
        } else if (iconday.equals("410")) {
            textView.setText(Html.fromHtml("&#xF12A;"));
        } else if (iconday.equals("456")) {
            textView.setText(Html.fromHtml("&#xF12B;"));
        } else if (iconday.equals("457")) {
            textView.setText(Html.fromHtml("&#xF12C;"));
        } else if (iconday.equals("499")) {
            textView.setText(Html.fromHtml("&#xF12D;"));
        } else if (iconday.equals("500")) {
            textView.setText(Html.fromHtml("&#xF12E;"));
        } else if (iconday.equals("501")) {
            textView.setText(Html.fromHtml("&#xF12F;"));
        } else if (iconday.equals("502")) {
            textView.setText(Html.fromHtml("&#xF130;"));
        } else if (iconday.equals("503")) {
            textView.setText(Html.fromHtml("&#xF131;"));
        } else if (iconday.equals("504")) {
            textView.setText(Html.fromHtml("&#xF132;"));
        } else if (iconday.equals("507")) {
            textView.setText(Html.fromHtml("&#xF133;"));
        } else if (iconday.equals("508")) {
            textView.setText(Html.fromHtml("&#xF134;"));
        } else if (iconday.equals("509")) {
            textView.setText(Html.fromHtml("&#xF135;"));
        } else if (iconday.equals("510")) {
            textView.setText(Html.fromHtml("&#xF136;"));
        } else if (iconday.equals("511")) {
            textView.setText(Html.fromHtml("&#xF137;"));
        } else if (iconday.equals("512")) {
            textView.setText(Html.fromHtml("&#xF138;"));
        } else if (iconday.equals("513")) {
            textView.setText(Html.fromHtml("&#xF139;"));
        } else if (iconday.equals("514")) {
            textView.setText(Html.fromHtml("&#xF13A;"));
        } else if (iconday.equals("515")) {
            textView.setText(Html.fromHtml("&#xF13B;"));
        } else if (iconday.equals("900")) {
            textView.setText(Html.fromHtml("&#xF144;"));
        } else if (iconday.equals("901")) {
            textView.setText(Html.fromHtml("&#xF145;"));
        } else {
            textView.setText(Html.fromHtml("&#xF146;"));
        }
    }

    class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.ViewHelder> {


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
            SettingsIcon(holder.binding.img, hourlyDTO.getIcon());

            holder.binding.hourTemperature.setText(hourlyDTO.getTemp() + "°");

        }

        @Override
        public int getItemCount() {

            return mWeatherData != null ? mWeatherData.size() : 0;
        }

        class ViewHelder extends RecyclerView.ViewHolder {

            TodayWeatherItemBinding binding;
            View WeatherView;

            public ViewHelder(@NonNull View itemView) {
                super(itemView);
                binding = TodayWeatherItemBinding.bind(itemView);
                WeatherView = itemView;
            }
        }
    }

    private class SeverDayWeatherAdapter extends RecyclerView.Adapter<SeverDayWeatherAdapter.ViewHolder> {

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
            holder.binding.img.setTypeface(MainActivity.getFont());
            SettingsIcon(holder.binding.img,dailyDTO.getIconDay());

            //设置温度
            holder.binding.dayTemperature.setText(dailyDTO.getTempMin() + "° / " + dailyDTO.getTempMax() + "°");

        }

        @Override
        public int getItemCount() {
            return mWeatherData != null ? mWeatherData.size() : 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            SeverDataWeatherItemBinding binding;

            View WeatherView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                binding = SeverDataWeatherItemBinding.bind(itemView);
                WeatherView = itemView;
            }
        }

    }

}
