package com.semicolon.africa.utils;

import com.semicolon.africa.DTOs.request.DryCleanerAddOrderRequest;
import com.semicolon.africa.data.model.DryCleaner;

public class Mapper {

    public static void map(DryCleanerAddOrderRequest dryCleanerAddOrderRequest, DryCleaner dryCleaner) {
        dryCleaner.setFirstName(dryCleanerAddOrderRequest.getFirstName());
        dryCleaner.setLastName(dryCleanerAddOrderRequest.getLastName());
        dryCleaner.setEmail((dryCleanerAddOrderRequest.getEmail()));
        dryCleaner.setPhoneNumber(dryCleanerAddOrderRequest.getPhoneNumber());
        dryCleaner.setCompanyName(dryCleanerAddOrderRequest.getCompanyName());
    }
}
