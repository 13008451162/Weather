package com.example.weather.Logic.netWorkUtil;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名: weather
 * 文件名: WeatherData
 * 创建者: lukecc0
 * 创建时间:2023/9/20 下午2:12
 * 描述: 解析天气的外层数据，版本信息等
 */

@NoArgsConstructor
@Data
public class WeatherData {

    // 状态码
    @SerializedName("code")
    private String code;

    // 当前API的最近更新时间
    @SerializedName("updateTime")
    private String updateTime;

    // 当前数据的响应式页面，便于嵌入网站或应用
    @SerializedName("fxLink")
    private String fxLink;

    // 存储每日天气数据的列表
    @SerializedName("daily")
    private List<DailyDTO> daily;

    // 引用信息，包括数据来源和许可信息
    @SerializedName("refer")
    private ReferDTO refer;

    @NoArgsConstructor
    @Data
    public static class ReferDTO {
        // 原始数据来源，或数据源说明
        @SerializedName("sources")
        private List<String> sources;

        // 数据许可或版权声明
        @SerializedName("license")
        private List<String> license;
    }

    @NoArgsConstructor
    @Data
    public static class DailyDTO {
        // 预报日期
        @SerializedName("fxDate")
        private String fxDate;

        // 日出时间，在高纬度地区可能为空
        @SerializedName("sunrise")
        private String sunrise;

        // 日落时间，在高纬度地区可能为空
        @SerializedName("sunset")
        private String sunset;

        // 当天月升时间，可能为空
        @SerializedName("moonrise")
        private String moonrise;

        // 当天月落时间，可能为空
        @SerializedName("moonset")
        private String moonset;

        // 月相名称
        @SerializedName("moonPhase")
        private String moonPhase;

        // 月相图标代码
        @SerializedName("moonPhaseIcon")
        private String moonPhaseIcon;

        // 预报当天最高温度
        @SerializedName("tempMax")
        private String tempMax;

        // 预报当天最低温度
        @SerializedName("tempMin")
        private String tempMin;

        // 预报白天天气状况的图标代码
        @SerializedName("iconDay")
        private String iconDay;

        // 预报白天天气状况文字描述
        @SerializedName("textDay")
        private String textDay;

        // 预报夜间天气状况的图标代码
        @SerializedName("iconNight")
        private String iconNight;

        // 预报晚间天气状况文字描述
        @SerializedName("textNight")
        private String textNight;

        // 预报白天风向360角度
        @SerializedName("wind360Day")
        private String wind360Day;

        // 预报白天风向
        @SerializedName("windDirDay")
        private String windDirDay;

        // 预报白天风力等级
        @SerializedName("windScaleDay")
        private String windScaleDay;

        // 预报白天风速，公里/小时
        @SerializedName("windSpeedDay")
        private String windSpeedDay;

        // 预报夜间风向360角度
        @SerializedName("wind360Night")
        private String wind360Night;

        // 预报夜间当天风向
        @SerializedName("windDirNight")
        private String windDirNight;

        // 预报夜间风力等级
        @SerializedName("windScaleNight")
        private String windScaleNight;

        // 预报夜间风速，公里/小时
        @SerializedName("windSpeedNight")
        private String windSpeedNight;

        // 相对湿度，百分比数值
        @SerializedName("humidity")
        private String humidity;

        // 预报当天总降水量，默认单位：毫米
        @SerializedName("precip")
        private String precip;

        // 大气压强，默认单位：百帕
        @SerializedName("pressure")
        private String pressure;

        // 能见度，默认单位：公里
        @SerializedName("vis")
        private String vis;

        // 云量，百分比数值。可能为空
        @SerializedName("cloud")
        private String cloud;

        // 紫外线强度指数
        @SerializedName("uvIndex")
        private String uvIndex;

        public String getFxDate() {
            return fxDate;
        }

        public String getSunrise() {
            return sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public String getMoonrise() {
            return moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public String getMoonPhase() {
            return moonPhase;
        }

        public String getMoonPhaseIcon() {
            return moonPhaseIcon;
        }

        public String getTempMax() {
            return tempMax;
        }

        public String getTempMin() {
            return tempMin;
        }

        public String getIconDay() {
            return iconDay;
        }

        public String getTextDay() {
            return textDay;
        }

        public String getIconNight() {
            return iconNight;
        }

        public String getTextNight() {
            return textNight;
        }

        public String getWind360Day() {
            return wind360Day;
        }

        public String getWindDirDay() {
            return windDirDay;
        }

        public String getWindScaleDay() {
            return windScaleDay;
        }

        public String getWindSpeedDay() {
            return windSpeedDay;
        }

        public String getWind360Night() {
            return wind360Night;
        }

        public String getWindDirNight() {
            return windDirNight;
        }

        public String getWindScaleNight() {
            return windScaleNight;
        }

        public String getWindSpeedNight() {
            return windSpeedNight;
        }

        public String getHumidity() {
            return humidity;
        }

        public String getPrecip() {
            return precip;
        }

        public String getPressure() {
            return pressure;
        }

        public String getVis() {
            return vis;
        }

        public String getCloud() {
            return cloud;
        }

        public String getUvIndex() {
            return uvIndex;
        }

        @Override
        public String toString() {
            return "DailyDTO{" +
                    "fxDate='" + fxDate + '\'' +
                    ", sunrise='" + sunrise + '\'' +
                    ", sunset='" + sunset + '\'' +
                    ", moonrise='" + moonrise + '\'' +
                    ", moonset='" + moonset + '\'' +
                    ", moonPhase='" + moonPhase + '\'' +
                    ", moonPhaseIcon='" + moonPhaseIcon + '\'' +
                    ", tempMax='" + tempMax + '\'' +
                    ", tempMin='" + tempMin + '\'' +
                    ", iconDay='" + iconDay + '\'' +
                    ", textDay='" + textDay + '\'' +
                    ", iconNight='" + iconNight + '\'' +
                    ", textNight='" + textNight + '\'' +
                    ", wind360Day='" + wind360Day + '\'' +
                    ", windDirDay='" + windDirDay + '\'' +
                    ", windScaleDay='" + windScaleDay + '\'' +
                    ", windSpeedDay='" + windSpeedDay + '\'' +
                    ", wind360Night='" + wind360Night + '\'' +
                    ", windDirNight='" + windDirNight + '\'' +
                    ", windScaleNight='" + windScaleNight + '\'' +
                    ", windSpeedNight='" + windSpeedNight + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", precip='" + precip + '\'' +
                    ", pressure='" + pressure + '\'' +
                    ", vis='" + vis + '\'' +
                    ", cloud='" + cloud + '\'' +
                    ", uvIndex='" + uvIndex + '\'' +
                    '}';
        }
    }

    public List<DailyDTO> getDaily() {
        return daily;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "code='" + code + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", fxLink='" + fxLink + '\'' +
                ", daily=" + daily +
                ", refer=" + refer +
                '}';
    }
}