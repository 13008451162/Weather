package com.example.weather.Logic.netWorkUtil.WeatherAndRemind;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名: weather
 * 文件名: PopularCitiesData
 * 创建者: lukecc0
 * 创建时间:2023/9/22 下午9:35
 * 描述: TODO
 */

@NoArgsConstructor
@Data
public class PopularCitiesData {

    @SerializedName("code")
    private String code;
    @SerializedName("topCityList")
    private List<TopCityListDTO> topCityList;

    @NoArgsConstructor
    @Data
    public static class TopCityListDTO {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;
        @SerializedName("lat")
        private String lat;
        @SerializedName("lon")
        private String lon;

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


        public void setName(String name) {
            this.name = name;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }
    }

    public List<TopCityListDTO> getTopCityList() {
        //设置一个定位到当前的位置
        TopCityListDTO listDTO = new TopCityListDTO();
        listDTO.setName("定位");
        topCityList.add(0,listDTO);

        return topCityList;
    }


}
