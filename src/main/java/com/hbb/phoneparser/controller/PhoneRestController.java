package com.hbb.phoneparser.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.hbb.phoneparser.dto.PhoneMaskResponse;
import com.hbb.phoneparser.service.MaskPhoneNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phone")
public class PhoneRestController {
    Logger LOG = LoggerFactory.getLogger(PhoneRestController.class);

    @Autowired
    MaskPhoneNumberService maskPhoneNumberService;

    @GetMapping
    @RequestMapping("/mask/{phoneNumber}")
    public ResponseEntity<PhoneMaskResponse> mask(@PathVariable("phoneNumber") String rawPhoneNumber) throws JsonProcessingException {
        LOG.info("Request: {}", rawPhoneNumber);

        PhoneMaskResponse phoneMaskResponse = maskPhoneNumberService.maskPhoneNumber(rawPhoneNumber);

        LOG.info("Response: '{}'", new ObjectMapper().writeValueAsString(phoneMaskResponse));
        return ResponseEntity.ok(phoneMaskResponse);
    }

    @GetMapping
    @RequestMapping("/mask-google/{phoneNumber}")
    public ResponseEntity<PhoneMaskResponse> maskGoogle(@PathVariable("phoneNumber") String rawPhoneNumber) throws JsonProcessingException, NumberParseException {
        LOG.info("Request: {}", rawPhoneNumber);
        PhoneMaskResponse phoneMaskResponse = new PhoneMaskResponse();
        phoneMaskResponse.setRawPhoneNumber(rawPhoneNumber);
        if(!rawPhoneNumber.startsWith("+")){
            rawPhoneNumber = "+"+rawPhoneNumber;
        }

        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(rawPhoneNumber, "ZZ");
        String formatted = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);

        phoneMaskResponse.setMaskedFullPhoneNumber(formatted);
        phoneMaskResponse.setCountryCallingCode(String.valueOf(phoneNumber.getCountryCode()));
        phoneMaskResponse.setPhoneNumber(String.valueOf(phoneNumber.getNationalNumber()));

        LOG.info("Response: '{}'", new ObjectMapper().writeValueAsString(phoneMaskResponse));
        return ResponseEntity.ok(phoneMaskResponse);
    }

}
