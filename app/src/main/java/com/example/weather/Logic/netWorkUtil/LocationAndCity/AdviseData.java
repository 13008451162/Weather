package com.example.weather.Logic.netWorkUtil.LocationAndCity;

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
public class AdviseData {


    @SerializedName("code")
    private String code;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("fxLink")
    private String fxLink;
    @SerializedName("daily")
    private List<DailyDTO> daily;

    @NoArgsConstructor
    @Data
    public static class DailyDTO {
        @SerializedName("date")
        private String date;
        @SerializedName("type")
        private String type;
        @SerializedName("name")
        private String name;
        @SerializedName("level")
        private String level;
        @SerializedName("category")
        private String category;
        @SerializedName("text")
        private String text;

        public String getDate() {
            return date;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public String getLevel() {
            return level;
        }

        public String getCategory() {
            return category;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "DailyDTO{" +
                    "date='" + date + '\'' +
                    ", type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    ", level='" + level + '\'' +
                    ", category='" + category + '\'' +
                    ", text='" + text + '\'' +
                    '}';
        }
    }

    public List<DailyDTO> getDaily() {
        return daily;
    }
}