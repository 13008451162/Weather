package com.example.weather.Logic.netWorkUtil;

import java.io.IOException;
import java.util.List;

/**
 * 项目名: weather
 * 文件名: LocationDataCallback
 * 创建者: lukecc0
 * 创建时间:2023/9/22 下午2:31
 * 描述: 正确或者错误返回数据的回调接口
 */

public interface LocationDataCallback {
    //成功返回数据
    void onSuccess( List<LocationData.LocationDTO> locationList);

    //未成功返回数据
    void onFailure(IOException e);
}
