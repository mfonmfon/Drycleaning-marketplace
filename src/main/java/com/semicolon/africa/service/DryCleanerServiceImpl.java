package com.semicolon.africa.service;

import com.semicolon.africa.data.model.DryCleaner;
import com.semicolon.africa.dto.request.DryCleanerSendAnOrderRequest;
import com.semicolon.africa.dto.request.UpdateDryCleanerOrderRequest;
import com.semicolon.africa.dto.response.DeleteDryCleanerOrderResponse;
import com.semicolon.africa.dto.response.DryCleanerSendAnOrderResponse;
import com.semicolon.africa.dto.response.UpdateDryCleanerOrderResponse;
import org.springframework.stereotype.Service;

@Service
public class DryCleanerServiceImpl implements DryCleanerService{
    @Override
    public DryCleanerSendAnOrderResponse sendDryCleanerOrder(DryCleanerSendAnOrderRequest dryCleanerSendAnOrderRequest) {
        return null;
    }

    @Override
    public UpdateDryCleanerOrderResponse updateDryCleanerOrder(UpdateDryCleanerOrderRequest updateDryCleanerOrderRequest) {
        return null;
    }

    @Override
    public DeleteDryCleanerOrderResponse deleteDryCleanerOrder(String id) {
        return null;
    }

    @Override
    public DryCleaner findDryCleanerBy(Long dryCleanerId) {
        return null;
    }
}
