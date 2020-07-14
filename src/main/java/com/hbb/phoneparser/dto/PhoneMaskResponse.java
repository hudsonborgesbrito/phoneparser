package com.hbb.phoneparser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneMaskResponse {
    private String rawPhoneNumber;
    private String maskedFullPhoneNumber;
    private String countryCallingCode;
    private String phoneNumber;
}
