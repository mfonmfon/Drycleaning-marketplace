package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.*;
import com.semicolon.africa.DTOs.response.*;
import com.semicolon.africa.data.model.DryCleaner;

import java.util.List;

public interface DryCleanerService {
    PostServiceResponse postService(PostServiceRequest postServiceRequest);
    ReceiveOrderResponse receiveOrder(ReceiveOrderRequest receiveOrderRequest);

    DryCleanerLogoutResponse logout();

    DryCleanerRegisterResponse register(DryCleanerRegisterRequest dryCleanerRegisterRequest);

    DryCleanerLoginResponse login(DryCleanerLoginRequest dryCleanerLoginRequest);

    List<DryCleaner> findAllDryCleaners();

    List<DryCleaner> findDryCleanerByFullName(String fullName);

    List<DryCleaner> findDryCleanerByCompanyName(String companyName);
    List<DryCleaner> findDyrCleanerByPhoneNumber(String phoneNumber);
    Long countAllDryCleaners();


}
