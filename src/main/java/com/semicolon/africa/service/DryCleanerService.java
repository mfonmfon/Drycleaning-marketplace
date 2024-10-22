package com.semicolon.africa.service;

import com.semicolon.africa.data.model.DryCleaner;
import com.semicolon.africa.dto.request.DryCleanerSendAnOrderRequest;
import com.semicolon.africa.dto.request.UpdateDryCleanerOrderRequest;
import com.semicolon.africa.dto.response.DeleteDryCleanerOrderResponse;
import com.semicolon.africa.dto.response.DryCleanerSendAnOrderResponse;
import com.semicolon.africa.dto.response.UpdateDryCleanerOrderResponse;

public interface DryCleanerService {

    DryCleanerSendAnOrderResponse sendDryCleanerOrder(DryCleanerSendAnOrderRequest dryCleanerSendAnOrderRequest);

    UpdateDryCleanerOrderResponse updateDryCleanerOrder(UpdateDryCleanerOrderRequest updateDryCleanerOrderRequest);

    DeleteDryCleanerOrderResponse deleteDryCleanerOrder(String id);

    DryCleaner findDryCleanerBy(Long dryCleanerId);
}
