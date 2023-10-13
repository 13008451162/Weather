package com.example.weather.Util;

import com.example.weather.Logic.netWorkUtil.WeatherAndRemind.LocationData;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: LocationUtility
 * 创建者: lukecc0
 * 创建时间:2023/9/20 下午3:52
 * 描述: TODO
 */

public class LocationUtility {
    private LocationData locationData;

    String jsonData = "{\n" +
            "    \"code\": \"200\",\n" +
            "    \"location\": [\n" +
            "        {\n" +
            "            \"name\": \"汉中\",\n" +
            "            \"id\": \"101110801\",\n" +
            "            \"lat\": \"33.07767\",\n" +
            "            \"lon\": \"107.02862\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"13\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/hanzhong-101110801.html\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"勉县\",\n" +
            "            \"id\": \"101110803\",\n" +
            "            \"lat\": \"33.15562\",\n" +
            "            \"lon\": \"106.68018\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"33\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/mian-county-101110803.html\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"西乡\",\n" +
            "            \"id\": \"101110807\",\n" +
            "            \"lat\": \"32.98796\",\n" +
            "            \"lon\": \"107.76586\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"33\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/xixiang-101110807.html\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"宁强\",\n" +
            "            \"id\": \"101110809\",\n" +
            "            \"lat\": \"32.83081\",\n" +
            "            \"lon\": \"106.25739\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"33\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/ningqiang-101110809.html\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"城固\",\n" +
            "            \"id\": \"101110806\",\n" +
            "            \"lat\": \"33.15310\",\n" +
            "            \"lon\": \"107.32989\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"33\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/chenggu-101110806.html\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"洋县\",\n" +
            "            \"id\": \"101110805\",\n" +
            "            \"lat\": \"33.22328\",\n" +
            "            \"lon\": \"107.54997\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"33\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/yang-county-101110805.html\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"汉台\",\n" +
            "            \"id\": \"101110812\",\n" +
            "            \"lat\": \"33.07768\",\n" +
            "            \"lon\": \"107.02824\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"35\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/hantai-101110812.html\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"南郑\",\n" +
            "            \"id\": \"101110810\",\n" +
            "            \"lat\": \"33.00334\",\n" +
            "            \"lon\": \"106.94239\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"35\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/nanzheng-101110810.html\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"略阳\",\n" +
            "            \"id\": \"101110802\",\n" +
            "            \"lat\": \"33.32964\",\n" +
            "            \"lon\": \"106.15390\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"43\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/lueyang-101110802.html\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"name\": \"镇巴\",\n" +
            "            \"id\": \"101110811\",\n" +
            "            \"lat\": \"32.53585\",\n" +
            "            \"lon\": \"107.89531\",\n" +
            "            \"adm2\": \"汉中\",\n" +
            "            \"adm1\": \"陕西省\",\n" +
            "            \"country\": \"中国\",\n" +
            "            \"tz\": \"Asia/Shanghai\",\n" +
            "            \"utcOffset\": \"+08:00\",\n" +
            "            \"isDst\": \"0\",\n" +
            "            \"type\": \"city\",\n" +
            "            \"rank\": \"43\",\n" +
            "            \"fxLink\": \"https://www.qweather.com/weather/zhenba-101110811.html\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"refer\": {\n" +
            "        \"sources\": [\n" +
            "            \"QWeather\"\n" +
            "        ],\n" +
            "        \"license\": [\n" +
            "            \"QWeather Developers License\"\n" +
            "        ]\n" +
            "    }\n" +
            "}";
    /**
     * 解析服务器返回的json数据
     */
    @Test
    public void TestOne(){
        LocationUtility locationUtility = new LocationUtility();
        if(locationUtility.handleProvinceResponse(jsonData)){
            System.out.println(locationUtility.getLocationData().toString());
        }
    }

    private boolean handleProvinceResponse(String jsonData){
        //获取Gson实例
        Gson gson = new Gson();

        try{
            // 使用 Gson 解析 JSON 数据,将JSON数据解析为WeatherData对象
            locationData = gson.fromJson(jsonData, LocationData.class);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<LocationData.LocationDTO> getLocationData() {
        return locationData.getLocation();
    }
}
