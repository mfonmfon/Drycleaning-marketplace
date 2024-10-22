package com.semicolon.africa.service;

import com.semicolon.africa.dto.request.SendOrderRequest;
import com.semicolon.africa.dto.request.UpdateSendOrderRequest;
import com.semicolon.africa.dto.response.DeleteOrderResponse;
import com.semicolon.africa.dto.response.SendOrderResponse;
import com.semicolon.africa.dto.response.UpdateSendOrderResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl  implements  OrderService{
    @Override
    public SendOrderResponse placeOrder(SendOrderRequest sendOrderRequest) {
        return null;
    }

    @Override
    public UpdateSendOrderResponse updateOrder(UpdateSendOrderRequest updateSendOrderRequest) {
        return null;
    }

    @Override
    public DeleteOrderResponse deleteOrder(String id) {
        return null;
    }
}
