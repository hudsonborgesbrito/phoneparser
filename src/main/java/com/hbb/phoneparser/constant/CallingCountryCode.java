package com.hbb.phoneparser.constant;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

public enum CallingCountryCode {
    NANP("1","NANP","North American Numbering Plan",true),
    IRELAND("53","IE", "Ireland", false),
    BRAZIL("55","BR","Brazil",false),
    ECUADOR("593","EC","Ecuador",false),
    MACEDONIA("389","MK", "Macedonia", false),
    NETHERLANDS("31","NL", "Netherlands", false);

    @Getter
    String countryCode;
    @Getter
    String country;
    @Getter
    String countryFullName;

    @Getter
    boolean multipleCountries;

    CallingCountryCode(String countryCode, String country, String countryFullName, boolean hasMultipleCountries) {
        this.country = country;
        this.countryCode = countryCode;
        this.countryFullName = countryFullName;
        this.multipleCountries = hasMultipleCountries;
    }

    private static Map<String, CallingCountryCode> map;
    static {
        map = new HashMap<>();
        for(CallingCountryCode item : values()){
            map.put(item.countryCode, item);
        }
    }
    public static CallingCountryCode fromPrefix(String countryCode){
        return fromPrefix(countryCode, 1);
    }
    private static CallingCountryCode fromPrefix(String countryCode, int length){
        if (length == 0){
            length = 1;
        }
        CallingCountryCode callingCountryCode = map.get(countryCode.substring(0, length));
        if(callingCountryCode == null && length < 3){
            return fromPrefix(countryCode, length+1);
        }
        return callingCountryCode;
    }
}
