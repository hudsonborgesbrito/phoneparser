package com.hbb.phoneparser.constant;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public enum CallingCountryArea1 {
    A_52("52", "ME", "Mexico"),
    A_55("55", "Mexico", "Mexico"),
    A_201("201", "US", "NJ"),
    A_204("204", "CA", "MB"),
    A_636("636", "US", "MO"),
    A_671("671", "GU", "Guam");

    String areaCode;
    @Getter
    String country;
    String region;

    CallingCountryArea1(String areaCode, String country, String region){
        areaCode  = areaCode;
        country = country;
        region = region;
    }

    private static Map<String, CallingCountryArea1> map;
    static {
        map = new HashMap<>();
        for(CallingCountryArea1 item : values()){
            map.put(item.areaCode, item);
        }
    }
    public static CallingCountryArea1 fromNumber(String areaCode){
        return map.get(areaCode);
    }
}
