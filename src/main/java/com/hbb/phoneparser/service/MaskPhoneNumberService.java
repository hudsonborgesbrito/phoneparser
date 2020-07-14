package com.hbb.phoneparser.service;

import com.hbb.phoneparser.constant.CallingCountryArea1;
import com.hbb.phoneparser.constant.CallingCountryCode;
import com.hbb.phoneparser.dto.PhoneMaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MaskPhoneNumberService {
    Logger LOG = LoggerFactory.getLogger(MaskPhoneNumberService.class);

    public PhoneMaskResponse maskPhoneNumber(String rawPhoneNumber) {

        String prefix = rawPhoneNumber.substring(0, 3);

        LOG.info("3 digit Preffix={}", prefix);

        PhoneMaskResponse phoneMaskResponse = new PhoneMaskResponse();
        phoneMaskResponse.setRawPhoneNumber(rawPhoneNumber);
        phoneMaskResponse.setCountryCallingCode(getCountryCallingCodeFromPreffix(prefix));
        phoneMaskResponse.setPhoneNumber(rawPhoneNumber.substring(phoneMaskResponse.getCountryCallingCode().length()));
        phoneMaskResponse.setMaskedFullPhoneNumber(String.format("(+%s) %s",phoneMaskResponse.getCountryCallingCode(), phoneMaskResponse.getPhoneNumber()));

        return phoneMaskResponse;
    }

    private String getCountryCallingCodeFromPreffix(String prefix){
        CallingCountryCode callingCountryCode = CallingCountryCode.fromPrefix(prefix);
        if(callingCountryCode == null ){
            throw new IllegalArgumentException("Invalid Country Code");
        }
        return callingCountryCode.getCountryCode();
    }
}
