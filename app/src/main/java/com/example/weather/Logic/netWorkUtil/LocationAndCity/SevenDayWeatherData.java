package com.example.weather.Logic.netWorkUtil.LocationAndCity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 项目名: weather
 * 文件名: SevenDayWeatherData
 * 创建者: lukecc0
 * 创建时间:2023/10/12 下午10:12
 * 描述: TODO
 */

@NoArgsConstructor
@Data
public class SevenDayWeatherData {

    @SerializedName("code")
    private String code;
    @SerializedName("fxLink")
    private String fxLink;
    @SerializedName("daily")
    private List<DailyDTO> daily;

    @NoArgsConstructor
    @Data
    public static class DailyDTO {
        @SerializedName("fxDate")
        private String fxDate;
        @SerializedName("sunrise")
        private String sunrise;
        @SerializedName("sunset")
        private String sunset;
        @SerializedName("moonrise")
        private String moonrise;
        @SerializedName("moonset")
        private String moonset;
        @SerializedName("moonPhase")
        private String moonPhase;
        @SerializedName("moonPhaseIcon")
        private String moonPhaseIcon;
        @SerializedName("tempMax")
        private String tempMax;
        @SerializedName("tempMin")
        private String tempMin;
        @SerializedName("iconDay")
        private String iconDay;
        @SerializedName("textDay")
        private String textDay;
        @SerializedName("iconNight")
        private String iconNight;
        @SerializedName("textNight")
        private String textNight;
        @SerializedName("wind360Day")
        private String wind360Day;
        @SerializedName("windDirDay")
        private String windDirDay;
        @SerializedName("windScaleDay")
        private String windScaleDay;
        @SerializedName("windSpeedDay")
        private String windSpeedDay;
        @SerializedName("wind360Night")
        private String wind360Night;
        @SerializedName("windDirNight")
        private String windDirNight;
        @SerializedName("windScaleNight")
        private String windScaleNight;
        @SerializedName("windSpeedNight")
        private String windSpeedNight;
        @SerializedName("humidity")
        private String humidity;
        @SerializedName("precip")
        private String precip;
        @SerializedName("pressure")
        private String pressure;
        @SerializedName("vis")
        private String vis;
        @SerializedName("cloud")
        private String cloud;
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

        public void setFxDate(String fxDate) {
            this.fxDate = fxDate;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public void setMoonrise(String moonrise) {
            this.moonrise = moonrise;
        }

        public void setMoonset(String moonset) {
            this.moonset = moonset;
        }

        public void setMoonPhase(String moonPhase) {
            this.moonPhase = moonPhase;
        }

        public void setMoonPhaseIcon(String moonPhaseIcon) {
            this.moonPhaseIcon = moonPhaseIcon;
        }

        public void setTempMax(String tempMax) {
            this.tempMax = tempMax;
        }

        public void setTempMin(String tempMin) {
            this.tempMin = tempMin;
        }

        public void setIconDay(String iconDay) {
            this.iconDay = iconDay;
        }

        public void setTextDay(String textDay) {
            this.textDay = textDay;
        }

        public void setIconNight(String iconNight) {
            this.iconNight = iconNight;
        }

        public void setTextNight(String textNight) {
            this.textNight = textNight;
        }

        public void setWind360Day(String wind360Day) {
            this.wind360Day = wind360Day;
        }

        public void setWindDirDay(String windDirDay) {
            this.windDirDay = windDirDay;
        }

        public void setWindScaleDay(String windScaleDay) {
            this.windScaleDay = windScaleDay;
        }

        public void setWindSpeedDay(String windSpeedDay) {
            this.windSpeedDay = windSpeedDay;
        }

        public void setWind360Night(String wind360Night) {
            this.wind360Night = wind360Night;
        }

        public void setWindDirNight(String windDirNight) {
            this.windDirNight = windDirNight;
        }

        public void setWindScaleNight(String windScaleNight) {
            this.windScaleNight = windScaleNight;
        }

        public void setWindSpeedNight(String windSpeedNight) {
            this.windSpeedNight = windSpeedNight;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public void setUvIndex(String uvIndex) {
            this.uvIndex = uvIndex;
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
}
