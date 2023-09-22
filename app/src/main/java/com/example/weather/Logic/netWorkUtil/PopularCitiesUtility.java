package com.example.weather.Logic.netWorkUtil;

import androidx.annotation.NonNull;

import com.example.weather.Ui.Place.PlaceAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名: weather
 * 文件名: PopularCitiesUtility
 * 创建者: lukecc0
 * 创建时间:2023/9/22 下午9:39
 * 描述: 用于模获取热门城市位置等地理信息
 */

public class PopularCitiesUtility {
    private PopularCitiesData popularCitiesData;
    private String JosnData;

    public PopularCitiesUtility(String josnData) {
        JosnData = josnData;
    }

    public boolean handleLocationResponse() {
        Gson gson = new Gson();

        try {
            popularCitiesData = gson.fromJson(JosnData, PopularCitiesData.class);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //返回地址信息的链表
    public List<PopularCitiesData.TopCityListDTO> getLocationDataList() {
        return popularCitiesData.getTopCityList();
    }

    public static void SendAddress(String address, final PopularCitiesDataCallback callback) {
        HttpUtil.SendOkhttpRequest(address, new okhttp3.Callback() {
                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        //得到服务器返回内容，判断是否访问到服务器
                        if (response.isSuccessful()) {
                            //从服务器获取数据进行解析
                            String responseBody = response.body().string();
                            PopularCitiesUtility utility;
                            try {
                                utility = new PopularCitiesUtility(responseBody);
                                //判断是否成功解析数据，若成功解析则回调返回数据
                                if (utility.handleLocationResponse()) {
                                    List<PopularCitiesData.TopCityListDTO> popularCitiesData = utility.getLocationDataList();
                                    callback.onSuccess(popularCitiesData);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                //释放对象，防止内存泄漏
                                utility = null;
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        //处理请求失败的异常情况
                        callback.onFailure(e);
                    }
                }
        );
    }


}
