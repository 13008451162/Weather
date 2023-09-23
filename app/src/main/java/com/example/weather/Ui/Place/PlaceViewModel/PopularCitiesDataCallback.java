package com.example.weather.Ui.Place.PlaceViewModel;

import com.example.weather.Logic.netWorkUtil.PopularCitiesData;

import java.io.IOException;
import java.util.List;

/**
 * 项目名: weather
 * 文件名: PopularCitiesDataCallback
 * 创建者: lukecc0
 * 创建时间:2023/9/22 下午9:39
 * 描述: 正确或者错误返回数据的回调接口返回热门城市
 */

public interface PopularCitiesDataCallback {

    /**
     * 成功返回数据
     * @param popularCitiesList 保存热门城市信息的list
     */
    void onSuccess( List<PopularCitiesData.TopCityListDTO> popularCitiesList);

    /**
     * 未成功返回数据
     * @param e 错误的异常类型
     */
    void onFailure(IOException e);
}
