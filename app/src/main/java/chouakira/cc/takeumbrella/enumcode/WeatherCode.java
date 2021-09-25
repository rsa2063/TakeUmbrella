package chouakira.cc.takeumbrella.enumcode;

/**
 * Created by rsa on 2017/8/14.
 */

public enum WeatherCode {

    NotRain(0, "Not Rain"),

    Rain(1, "Rain");

    int code;

    String label;

    WeatherCode(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return getLabel();
    }
}
