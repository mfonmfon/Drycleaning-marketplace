package com.semicolon.africa.service.interfaces;

import com.semicolon.africa.DTOs.request.PlaceOrderRequest;
import com.semicolon.africa.DTOs.request.UpdatePlaceOrderRequest;
import com.semicolon.africa.DTOs.response.DeletePlacedOrderResponse;
import com.semicolon.africa.DTOs.response.PlaceOrderResponse;
import com.semicolon.africa.DTOs.response.UpdatePlaceOrderResponse;
import com.semicolon.africa.data.model.OrderPlacement;

import java.util.List;

public interface OrderPlacementService {
    PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest);
    UpdatePlaceOrderResponse updateOrder(UpdatePlaceOrderRequest placeOrderRequest);
    DeletePlacedOrderResponse deleteOrder(Long id);
    Long countAllOrderPlaced();
    List<OrderPlacement> allOrderPlaced();
    Long getOrderById(Long id);
}
