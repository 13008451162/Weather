package com.example.weather.Ui.Place.PlaceViewModel;

import androidx.lifecycle.ViewModel;

import com.example.weather.Logic.netWorkUtil.WeatherAndRemind.PopularCitiesData;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: viewmodel
 * 创建者: lukecc0
 * 创建时间:2023/9/26 下午12:43
 * 描述: TODO
 */

public class CityUiViewmodel extends ViewModel {
    String url = "";

    List<PopularCitiesData.TopCityListDTO> popularCitiesList;

    public List<PopularCitiesData.TopCityListDTO> getPopularCitiesList() {
        return popularCitiesList;
    }

    public void setPopularCitiesList(List<PopularCitiesData.TopCityListDTO> popularCitiesList) {
        if (popularCitiesList != null)
            this.popularCitiesList = popularCitiesList;
    }

    public void setUrl(String url1) {
        if (url1 != null) {
            this.url = url1;
        }
    }

    public String getUrl() {
        return url;
    }
}
