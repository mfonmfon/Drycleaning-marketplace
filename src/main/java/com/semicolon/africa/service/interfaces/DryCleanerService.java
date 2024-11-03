package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.DryCleanerAddOrderRequest;
import com.semicolon.africa.DTOs.request.DryCleanerLoginRequest;
import com.semicolon.africa.DTOs.request.DryCleanerRegisterRequest;
import com.semicolon.africa.DTOs.request.DryCleanerUpdateOrderRequest;
import com.semicolon.africa.DTOs.response.*;
import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.model.DryCleaner;

import java.util.List;

public interface DryCleanerService {
    DryCleanerAddOrderResponse sendOrder(DryCleanerAddOrderRequest dryCleanerAddOrderRequest);
    DryCleanerUpdateOrderResponse updateOrder(DryCleanerUpdateOrderRequest dryCleanerUpdateOrderRequest);
    DryCleanerDeleteOrderResponse deleteOrder(Long id);
    DryCleanerRegisterResponse register(DryCleanerRegisterRequest dryCleanerRegisterRequest);
    DryCleanerLoginResponse login(DryCleanerLoginRequest dryCleanerLoginRequest);
//    CheckForRiderAvailabilty isAvailable();
    List<DryCleaner> findAllDryCleaners();
    List<DryCleaner> findDryCleanerByFirstName(String firstName);
    List<DryCleaner> findDryCleanerByLastName(String lastName);
    List<DryCleaner> findDryCleanerByFirstNameAndLastName(String firstName, String lastName);
    List<DryCleaner> findDryCleanerByCompanyName(String companyName);
    List<DryCleaner> findDyrCleanerByPhoneNumber(String phoneNumber);
    Long countAllDryCleaners();


}
