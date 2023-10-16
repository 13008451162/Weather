package com.example.weather.Logic.netWorkUtil.LocationAndCity;

import com.example.weather.Logic.netWorkUtil.GenericUtility;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.List;

/**
 * 项目名: weather
 * 文件名: Utility
 * 创建者: lukecc0
 * 创建时间:2023/9/19 下午3:01
 * 描述: 获取近7天的天气数据
 */

public class AdviseDataUtility extends GenericUtility {

    public AdviseDataUtility(String jsonData) {
        super(jsonData);
    }

    public AdviseDataUtility() {
    }

    @Override
    protected GenericUtility createUtility(String jsonData) {
        return new AdviseDataUtility(jsonData);
    }

    @Override
    protected boolean handJsonParse() {
        //获取Gson实例
        Gson gson = new Gson();

        try {
            data = gson.fromJson(jsonData, AdviseData.class);
            return true;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<AdviseData.DailyDTO> getDataList() {
        return ((AdviseData)data).getDaily();
    }
}
