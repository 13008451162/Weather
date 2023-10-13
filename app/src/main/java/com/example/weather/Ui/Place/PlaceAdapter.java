package com.example.weather.Ui.Place;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Logic.netWorkUtil.WeatherAndRemind.LocationData;
import com.example.weather.R;
import com.example.weather.WeatherApplication;
import com.example.weather.databinding.PlaceItemBinding;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: PlaceAdapter
 * 创建者: lukecc0
 * 创建时间:2023/9/21 下午2:59
 * 描述: 一个用于显示城市数据的适配器
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<LocationData.LocationDTO> mLocationData;

    public PlaceAdapter(List<LocationData.LocationDTO> mLocationData) {
        this.mLocationData = mLocationData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_item, parent, false);

        //防止被修改
        final ViewHolder holder = new ViewHolder(view);

        //注册外层监听事件
        holder.CityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通过hold可以获取适配器的位置
                int position = holder.getAdapterPosition();

                //取得相应的经纬度
                LocationData.LocationDTO locationDTO = mLocationData.get(position);
                Toast.makeText(WeatherApplication.getContext(),locationDTO.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocationData.LocationDTO locationDTO = mLocationData.get(position);

        //设置城市信息
        holder.binding.City.setText(locationDTO.getName());
        holder.binding.CityDetails.setText(locationDTO.getCountry()
                                + "  " + locationDTO.getAdm1()
                                + "  " + locationDTO.getAdm2());
    }

    @Override
    public int getItemCount() {
        return mLocationData != null ? mLocationData.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        PlaceItemBinding binding;
        View CityView;  //外层的View，用于设置点击事件
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = PlaceItemBinding.bind(itemView);  //选择项
            CityView = itemView;
        }
    }
}
