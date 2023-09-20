package com.example.weather.Util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 项目名: weather
 * 文件名: SendOkhttpRequest
 * 创建者: lukecc0
 * 创建时间:2023/9/19 下午2:46
 * 描述: 用于从服务器获取市县的数据
 */

public class HttpUtil {
    public static void SendOkhttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
