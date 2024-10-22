package com.semicolon.africa.service;

import com.semicolon.africa.data.model.Order;
import com.semicolon.africa.dto.request.UpdateSendOrderRequest;
import com.semicolon.africa.dto.request.SendOrderRequest;
import com.semicolon.africa.dto.response.DeleteOrderResponse;
import com.semicolon.africa.dto.response.SendOrderResponse;
import com.semicolon.africa.dto.response.UpdateSendOrderResponse;

public interface OrderService {


    SendOrderResponse placeOrder(SendOrderRequest sendOrderRequest);
    UpdateSendOrderResponse updateOrder(UpdateSendOrderRequest updateSendOrderRequest);
    DeleteOrderResponse deleteOrder(String id);
    Order findOrderBy(Long orderId);

}
