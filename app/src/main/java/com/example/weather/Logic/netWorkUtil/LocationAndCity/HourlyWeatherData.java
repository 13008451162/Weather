package com.example.weather.Logic.netWorkUtil.LocationAndCity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名: weather
 * 文件名: HourlyWeatherData
 * 创建者: lukecc0
 * 创建时间:2023/10/7 下午9:01
 * 描述: TODO
 */

@NoArgsConstructor
@Data
public class HourlyWeatherData {


    @SerializedName("code")
    private String code;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("fxLink")
    private String fxLink;
    @SerializedName("hourly")
    private List<HourlyDTO> hourly;

    @NoArgsConstructor
    @Data
    public static class HourlyDTO {
        @SerializedName("fxTime")
        private String fxTime;
        @SerializedName("temp")
        private String temp;
        @SerializedName("icon")
        private String icon;
        @SerializedName("text")
        private String text;

        public HourlyDTO(HourlyDTO hourlyDTO) {
            this.fxTime = hourlyDTO.fxTime;
            this.icon = hourlyDTO.icon;
            this.text = hourlyDTO.text;
            this.temp = hourlyDTO.temp;
        }

        public HourlyDTO() {
        }

        public String getFxTime() {
            return fxTime;
        }

        public String getTemp() {
            return temp;
        }

        public String getIcon() {
            return icon;
        }

        public String getText() {
            return text;
        }

        public void setFxTime(String fxTime) {
            this.fxTime = fxTime;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setText(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "HourlyDTO{" +
                    "fxTime='" + fxTime + '\'' +
                    ", temp='" + temp + '\'' +
                    ", icon='" + icon + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

    public List<HourlyDTO> getHourly() {
        return hourly;
    }
}
