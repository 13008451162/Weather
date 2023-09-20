package com.example.weather.Util;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名: weather
 * 文件名: JsonData
 * 创建者: lukecc0
 * 创建时间:2023/9/19 下午3:12
 * 描述: 用于解析地区/城市信息的数据类
 */


@NoArgsConstructor
@Data
public class LocationData {

    @SerializedName("code")
    private String code;
    @SerializedName("location")
    private List<LocationDTO> location;
    @SerializedName("refer")
    private ReferDTO refer;

    @NoArgsConstructor
    @Data
    public static class ReferDTO {
        @SerializedName("sources")
        private List<String> sources;
        @SerializedName("license")
        private List<String> license;
    }

    @NoArgsConstructor
    @Data
    public static class LocationDTO {
        @SerializedName("name")
        private String name; // 地区/城市名称

        @SerializedName("id")
        private String id; // 地区/城市ID

        @SerializedName("lat")
        private String lat; // 地区/城市纬度

        @SerializedName("lon")
        private String lon; // 地区/城市经度

        @SerializedName("adm2")
        private String adm2; // 地区/城市的上级行政区划名称

        @SerializedName("adm1")
        private String adm1; // 地区/城市所属一级行政区域

        @SerializedName("country")
        private String country; // 地区/城市所属国家名称

        @SerializedName("tz")
        private String tz; // 地区/城市所在时区

        @SerializedName("utcOffset")
        private String utcOffset; // 地区/城市目前与UTC时间偏移的小时数，参考详细说明

        @SerializedName("isDst")
        private String isDst; // 地区/城市是否当前处于夏令时。1 表示当前处于夏令时，0 表示当前不是夏令时。

        @SerializedName("type")
        private String type; // 地区/城市的属性

        @SerializedName("rank")
        private String rank; // 地区评分

        @SerializedName("fxLink")
        private String fxLink; // 该地区的天气预报网页链接，便于嵌入你的网站或应用

        // 自动生成的getter方法，用于获取字段值

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public String getLat() {
            return lat;
        }

        public String getLon() {
            return lon;
        }

        public String getAdm2() {
            return adm2;
        }

        public String getAdm1() {
            return adm1;
        }

        public String getCountry() {
            return country;
        }

        public String getTz() {
            return tz;
        }

        public String getUtcOffset() {
            return utcOffset;
        }

        public String getIsDst() {
            return isDst;
        }

        public String getType() {
            return type;
        }

        public String getRank() {
            return rank;
        }

        public String getFxLink() {
            return fxLink;
        }

        @Override
        public String toString() {
            return "LocationDTO{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    ", lat='" + lat + '\'' +
                    ", lon='" + lon + '\'' +
                    ", adm2='" + adm2 + '\'' +
                    ", adm1='" + adm1 + '\'' +
                    ", country='" + country + '\'' +
                    ", tz='" + tz + '\'' +
                    ", utcOffset='" + utcOffset + '\'' +
                    ", isDst='" + isDst + '\'' +
                    ", type='" + type + '\'' +
                    ", rank='" + rank + '\'' +
                    ", fxLink='" + fxLink + '\'' +
                    '}';
        }
    }

    // 自动生成的toString方法，用于将对象转换为字符串表示
    @Override
    public String toString() {
        return "LocationData{" +
                "code='" + code + '\'' +
                ", location=" + location +
                ", refer=" + refer +
                '}';
    }

    // 自动生成的getter方法，用于获取地区/城市信息列表
    public List<LocationDTO> getLocation() {
        return location;
    }
}
