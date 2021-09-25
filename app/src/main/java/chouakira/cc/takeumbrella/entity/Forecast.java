package chouakira.cc.takeumbrella.entity;

import java.util.Date;

import chouakira.cc.takeumbrella.enumcode.WeatherCode;
import chouakira.cc.takeumbrella.util.Const;

/**
 * Created by rsa on 2017/8/27.
 */

public class Forecast {
    public WeatherCode getWeather() {
        return weather;
    }

    public void setWeather(WeatherCode weather) {
        this.weather = weather;
    }

    WeatherCode weather;

    //307.53K - 273.15 = 34.38°C
    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    float temperature;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    Date time;


    @Override
    public String toString() {
        return String.format("Time is %s; Temperature is %s°C; Weather is %s"
                , Const.sdf.format(getTime()), getTemperature(), getWeather().toString());
    }
}
