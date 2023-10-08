package com.example.weather.Ui.Place.PlaceViewModel;

import java.io.IOException;
import java.util.List;

/**
 * 项目名: weather
 * 文件名: DataCallback
 * 创建者: lukecc0
 * 创建时间:2023/10/7 下午2:57
 * 描述:  返回从网络请求获取的数据的回调接口
 */

public interface DataCallback<T> {

    void onSuccess(List<T> dataList);

    void onFailure(IOException e);
}