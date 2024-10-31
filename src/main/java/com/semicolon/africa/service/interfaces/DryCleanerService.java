package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.DryCleanerAddOrderRequest;
import com.semicolon.africa.DTOs.request.DryCleanerLoginRequest;
import com.semicolon.africa.DTOs.request.DryCleanerRegisterRequest;
import com.semicolon.africa.DTOs.request.DryCleanerUpdateOrderRequest;
import com.semicolon.africa.DTOs.response.*;

public interface DryCleanerService {

    DryCleanerAddOrderResponse sendOrder(DryCleanerAddOrderRequest dryCleanerAddOrderRequest);
    DryCleanerUpdateOrderResponse updateOrder(DryCleanerUpdateOrderRequest dryCleanerUpdateOrderRequest);
    DryCleanerDeleteOrderResponse deleteOrder(String id);
    DryCleanerRegisterResponse register(DryCleanerRegisterRequest dryCleanerRegisterRequest);
    DryCleanerLoginResponse login(DryCleanerLoginRequest dryCleanerLoginRequest);
    CheckForRiderAvailabilty isAvailable();

}
