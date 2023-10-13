package com.example.weather.Util;

import com.example.weather.Logic.netWorkUtil.LocationAndCity.WeatherData;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: Utility
 * 创建者: lukecc0
 * 创建时间:2023/9/19 下午3:01
 * 描述: TODO
 */

public class WeatherUtility {

    private WeatherData DayWeatherSum;

    private String jsonData = "{\n" +
            "    \"code\": \"200\",\n" +
            "    \"updateTime\": \"2023-09-20T15:35+08:00\",\n" +
            "    \"fxLink\": \"https://www.qweather.com/weather/hanzhong-101110801.html\",\n" +
            "    \"daily\": [\n" +
            "        {\n" +
            "            \"fxDate\": \"2023-09-20\",\n" +
            "            \"sunrise\": \"06:35\",\n" +
            "            \"sunset\": \"18:58\",\n" +
            "            \"moonrise\": \"11:22\",\n" +
            "            \"moonset\": \"21:41\",\n" +
            "            \"moonPhase\": \"峨眉月\",\n" +
            "            \"moonPhaseIcon\": \"801\",\n" +
            "            \"tempMax\": \"22\",\n" +
            "            \"tempMin\": \"18\",\n" +
            "            \"iconDay\": \"305\",\n" +
            "            \"textDay\": \"小雨\",\n" +
            "            \"iconNight\": \"305\",\n" +
            "            \"textNight\": \"小雨\",\n" +
            "            \"wind360Day\": \"45\",\n" +
            "            \"windDirDay\": \"东北风\",\n" +
            "            \"windScaleDay\": \"1-3\",\n" +
            "            \"windSpeedDay\": \"16\",\n" +
            "            \"wind360Night\": \"45\",\n" +
            "            \"windDirNight\": \"东北风\",\n" +
            "            \"windScaleNight\": \"1-3\",\n" +
            "            \"windSpeedNight\": \"3\",\n" +
            "            \"humidity\": \"86\",\n" +
            "            \"precip\": \"1.0\",\n" +
            "            \"pressure\": \"934\",\n" +
            "            \"vis\": \"24\",\n" +
            "            \"cloud\": \"55\",\n" +
            "            \"uvIndex\": \"2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"fxDate\": \"2023-09-21\",\n" +
            "            \"sunrise\": \"06:36\",\n" +
            "            \"sunset\": \"18:56\",\n" +
            "            \"moonrise\": \"12:30\",\n" +
            "            \"moonset\": \"22:26\",\n" +
            "            \"moonPhase\": \"峨眉月\",\n" +
            "            \"moonPhaseIcon\": \"801\",\n" +
            "            \"tempMax\": \"24\",\n" +
            "            \"tempMin\": \"18\",\n" +
            "            \"iconDay\": \"100\",\n" +
            "            \"textDay\": \"晴\",\n" +
            "            \"iconNight\": \"104\",\n" +
            "            \"textNight\": \"阴\",\n" +
            "            \"wind360Day\": \"45\",\n" +
            "            \"windDirDay\": \"东北风\",\n" +
            "            \"windScaleDay\": \"1-3\",\n" +
            "            \"windSpeedDay\": \"16\",\n" +
            "            \"wind360Night\": \"45\",\n" +
            "            \"windDirNight\": \"东北风\",\n" +
            "            \"windScaleNight\": \"1-3\",\n" +
            "            \"windSpeedNight\": \"3\",\n" +
            "            \"humidity\": \"90\",\n" +
            "            \"precip\": \"0.0\",\n" +
            "            \"pressure\": \"932\",\n" +
            "            \"vis\": \"25\",\n" +
            "            \"cloud\": \"25\",\n" +
            "            \"uvIndex\": \"2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"fxDate\": \"2023-09-22\",\n" +
            "            \"sunrise\": \"06:36\",\n" +
            "            \"sunset\": \"18:55\",\n" +
            "            \"moonrise\": \"13:37\",\n" +
            "            \"moonset\": \"23:21\",\n" +
            "            \"moonPhase\": \"上弦月\",\n" +
            "            \"moonPhaseIcon\": \"802\",\n" +
            "            \"tempMax\": \"16\",\n" +
            "            \"tempMin\": \"15\",\n" +
            "            \"iconDay\": \"305\",\n" +
            "            \"textDay\": \"小雨\",\n" +
            "            \"iconNight\": \"305\",\n" +
            "            \"textNight\": \"小雨\",\n" +
            "            \"wind360Day\": \"45\",\n" +
            "            \"windDirDay\": \"东北风\",\n" +
            "            \"windScaleDay\": \"1-3\",\n" +
            "            \"windSpeedDay\": \"3\",\n" +
            "            \"wind360Night\": \"45\",\n" +
            "            \"windDirNight\": \"东北风\",\n" +
            "            \"windScaleNight\": \"1-3\",\n" +
            "            \"windSpeedNight\": \"3\",\n" +
            "            \"humidity\": \"95\",\n" +
            "            \"precip\": \"1.0\",\n" +
            "            \"pressure\": \"933\",\n" +
            "            \"vis\": \"22\",\n" +
            "            \"cloud\": \"55\",\n" +
            "            \"uvIndex\": \"2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"fxDate\": \"2023-09-23\",\n" +
            "            \"sunrise\": \"06:37\",\n" +
            "            \"sunset\": \"18:54\",\n" +
            "            \"moonrise\": \"14:40\",\n" +
            "            \"moonset\": \"\",\n" +
            "            \"moonPhase\": \"盈凸月\",\n" +
            "            \"moonPhaseIcon\": \"803\",\n" +
            "            \"tempMax\": \"19\",\n" +
            "            \"tempMin\": \"15\",\n" +
            "            \"iconDay\": \"305\",\n" +
            "            \"textDay\": \"小雨\",\n" +
            "            \"iconNight\": \"305\",\n" +
            "            \"textNight\": \"小雨\",\n" +
            "            \"wind360Day\": \"0\",\n" +
            "            \"windDirDay\": \"北风\",\n" +
            "            \"windScaleDay\": \"1-3\",\n" +
            "            \"windSpeedDay\": \"3\",\n" +
            "            \"wind360Night\": \"0\",\n" +
            "            \"windDirNight\": \"北风\",\n" +
            "            \"windScaleNight\": \"1-3\",\n" +
            "            \"windSpeedNight\": \"3\",\n" +
            "            \"humidity\": \"96\",\n" +
            "            \"precip\": \"3.0\",\n" +
            "            \"pressure\": \"931\",\n" +
            "            \"vis\": \"23\",\n" +
            "            \"cloud\": \"68\",\n" +
            "            \"uvIndex\": \"2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"fxDate\": \"2023-09-24\",\n" +
            "            \"sunrise\": \"06:38\",\n" +
            "            \"sunset\": \"18:52\",\n" +
            "            \"moonrise\": \"15:37\",\n" +
            "            \"moonset\": \"00:25\",\n" +
            "            \"moonPhase\": \"盈凸月\",\n" +
            "            \"moonPhaseIcon\": \"803\",\n" +
            "            \"tempMax\": \"18\",\n" +
            "            \"tempMin\": \"17\",\n" +
            "            \"iconDay\": \"305\",\n" +
            "            \"textDay\": \"小雨\",\n" +
            "            \"iconNight\": \"305\",\n" +
            "            \"textNight\": \"小雨\",\n" +
            "            \"wind360Day\": \"0\",\n" +
            "            \"windDirDay\": \"北风\",\n" +
            "            \"windScaleDay\": \"1-3\",\n" +
            "            \"windSpeedDay\": \"3\",\n" +
            "            \"wind360Night\": \"0\",\n" +
            "            \"windDirNight\": \"北风\",\n" +
            "            \"windScaleNight\": \"1-3\",\n" +
            "            \"windSpeedNight\": \"3\",\n" +
            "            \"humidity\": \"96\",\n" +
            "            \"precip\": \"5.1\",\n" +
            "            \"pressure\": \"934\",\n" +
            "            \"vis\": \"24\",\n" +
            "            \"cloud\": \"80\",\n" +
            "            \"uvIndex\": \"2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"fxDate\": \"2023-09-25\",\n" +
            "            \"sunrise\": \"06:38\",\n" +
            "            \"sunset\": \"18:51\",\n" +
            "            \"moonrise\": \"16:26\",\n" +
            "            \"moonset\": \"01:35\",\n" +
            "            \"moonPhase\": \"盈凸月\",\n" +
            "            \"moonPhaseIcon\": \"803\",\n" +
            "            \"tempMax\": \"18\",\n" +
            "            \"tempMin\": \"16\",\n" +
            "            \"iconDay\": \"305\",\n" +
            "            \"textDay\": \"小雨\",\n" +
            "            \"iconNight\": \"305\",\n" +
            "            \"textNight\": \"小雨\",\n" +
            "            \"wind360Day\": \"0\",\n" +
            "            \"windDirDay\": \"北风\",\n" +
            "            \"windScaleDay\": \"1-3\",\n" +
            "            \"windSpeedDay\": \"3\",\n" +
            "            \"wind360Night\": \"0\",\n" +
            "            \"windDirNight\": \"北风\",\n" +
            "            \"windScaleNight\": \"1-3\",\n" +
            "            \"windSpeedNight\": \"3\",\n" +
            "            \"humidity\": \"97\",\n" +
            "            \"precip\": \"5.1\",\n" +
            "            \"pressure\": \"933\",\n" +
            "            \"vis\": \"21\",\n" +
            "            \"cloud\": \"80\",\n" +
            "            \"uvIndex\": \"2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"fxDate\": \"2023-09-26\",\n" +
            "            \"sunrise\": \"06:39\",\n" +
            "            \"sunset\": \"18:49\",\n" +
            "            \"moonrise\": \"17:07\",\n" +
            "            \"moonset\": \"02:49\",\n" +
            "            \"moonPhase\": \"盈凸月\",\n" +
            "            \"moonPhaseIcon\": \"803\",\n" +
            "            \"tempMax\": \"21\",\n" +
            "            \"tempMin\": \"19\",\n" +
            "            \"iconDay\": \"305\",\n" +
            "            \"textDay\": \"小雨\",\n" +
            "            \"iconNight\": \"305\",\n" +
            "            \"textNight\": \"小雨\",\n" +
            "            \"wind360Day\": \"0\",\n" +
            "            \"windDirDay\": \"北风\",\n" +
            "            \"windScaleDay\": \"1-3\",\n" +
            "            \"windSpeedDay\": \"3\",\n" +
            "            \"wind360Night\": \"0\",\n" +
            "            \"windDirNight\": \"北风\",\n" +
            "            \"windScaleNight\": \"1-3\",\n" +
            "            \"windSpeedNight\": \"3\",\n" +
            "            \"humidity\": \"95\",\n" +
            "            \"precip\": \"6.3\",\n" +
            "            \"pressure\": \"935\",\n" +
            "            \"vis\": \"24\",\n" +
            "            \"cloud\": \"85\",\n" +
            "            \"uvIndex\": \"2\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"refer\": {\n" +
            "        \"sources\": [\n" +
            "            \"QWeather\"\n" +
            "        ],\n" +
            "        \"license\": [\n" +
            "            \"CC BY-SA 4.0\"\n" +
            "        ]\n" +
            "    }\n" +
            "}";


    @Test
    public void TestOne(){
        WeatherUtility weatherUtility = new WeatherUtility();
        if(weatherUtility.handleWeatherResponse(jsonData)){
            System.out.println(weatherUtility.getDailyWeatherList().toString());
        }
    }

    /**
     * 解析服务器返回的天气json数据
     * @param jsonData 天气的Json数据
     * @return 是否成功解析
     */
    private boolean handleWeatherResponse(String jsonData){
        //获取Gson实例
        Gson gson = new Gson();

        try{
            // 使用 Gson 解析 JSON 数据,将JSON数据解析为WeatherData对象
            DayWeatherSum = gson.fromJson(jsonData, WeatherData.class);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //获取每日天气
    public List<WeatherData.DailyDTO> getDailyWeatherList() {
        return DayWeatherSum.getDaily();
    }

    /**
     * 获取总数据，包括以下内容
     * code 请参考状态码
     * updateTime 当前API的最近更新时间
     * fxLink 当前数据的响应式页面，便于嵌入网站或应用
     * @return
     */
    public WeatherData getDayWeatherSum() {
        return DayWeatherSum;
    }
}
