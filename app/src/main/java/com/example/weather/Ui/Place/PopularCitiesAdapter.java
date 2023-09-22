package com.example.weather.Ui.Place;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.Logic.netWorkUtil.LocationData;
import com.example.weather.Logic.netWorkUtil.PopularCitiesData;
import com.example.weather.R;
import com.example.weather.WeatherApplication;
import com.example.weather.databinding.PopularCitiesItemBinding;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: PopularCitiesAdapter
 * 创建者: lukecc0
 * 创建时间:2023/9/22 下午10:00
 * 描述: 一个用于显示热门城市数据的适配器
 */

public class PopularCitiesAdapter extends RecyclerView.Adapter<PopularCitiesAdapter.ViewHolder> {

    private List<PopularCitiesData.TopCityListDTO> mPopularData;

    public PopularCitiesAdapter(List<PopularCitiesData.TopCityListDTO> mPopularData) {
        this.mPopularData = mPopularData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_cities_item, parent, false);

        //防止被修改
        final PopularCitiesAdapter.ViewHolder holder = new PopularCitiesAdapter.ViewHolder(view);

        //注册外层监听事件
        holder.PopularView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //通过hold可以获取适配器的位置
                int position = holder.getAdapterPosition();

                //取得相应的经纬度
                PopularCitiesData.TopCityListDTO cityListDTO = mPopularData.get(position);
                Toast.makeText(WeatherApplication.getContext(),cityListDTO.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularCitiesData.TopCityListDTO cityListDTO = mPopularData.get(position);
        holder.binding.PopularRecyclerViewCity.setText(cityListDTO.getName());
    }

    @Override
    public int getItemCount() {
        return mPopularData != null ? mPopularData.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        PopularCitiesItemBinding binding;

        View PopularView;   //外层的View

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = PopularCitiesItemBinding.bind(itemView);
            PopularView = itemView;
        }
    }
}
