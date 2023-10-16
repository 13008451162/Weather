package com.example.weather.Logic.netWorkUtil;


import androidx.annotation.NonNull;

import com.example.weather.TestTool.LogUtil;
import com.example.weather.Ui.Place.PlaceViewModel.DataCallback;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名: weather
 * 文件名: Util
 * 创建者: lukecc0
 * 创建时间:2023/10/7 下午2:32
 * 描述: 管理所有的网络请求方法
 */

public abstract class GenericUtility<T>{

    protected T data;
    protected String jsonData;

    public GenericUtility(String jsonData) {
        this.jsonData = jsonData;
    }

    public GenericUtility() {
    }

    /**
     * 用于回调服务器返回的内容，将数据返回给ViewModel层的回调接口
     * @param address  需要访问的服务器地址
     * @param callback 被操作的回调接口
     */
     public void sendAddress(String address, DataCallback callback){
         HttpUtil.SendOkhttpRequest(address, new okhttp3.Callback() {
             @Override
             public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                 if (response.isSuccessful()) {
                     String responseBody = response.body().string();
                     GenericUtility<T> utility;
                     try {
                         utility = createUtility(responseBody);
                         if (utility.handJsonParse()) {
                             List<T> dataList = utility.getDataList();
                             callback.onSuccess(dataList);
                         }
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }
             }

             @Override
             public void onFailure(@NonNull Call call, @NonNull IOException e) {
                 callback.onFailure(e);
             }
         });
     }

    /**
     * 得到一个继承该方法的子类，用于调用数据
     * @param jsonData 需要解析的Json数据
     * @return
     */
    protected abstract GenericUtility<T> createUtility(String jsonData);

    /**
     * 解析服务器返回的地址json数据
     * @return 是否成功解析
     */
    protected abstract boolean handJsonParse();

    /**
     * 返回解析Json后得到的数据链表
     * @return
     */
    public abstract List getDataList();


}
