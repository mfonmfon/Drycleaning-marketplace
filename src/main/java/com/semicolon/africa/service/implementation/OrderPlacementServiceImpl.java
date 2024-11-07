package com.semicolon.africa.service.implementation;

import com.semicolon.africa.DTOs.request.PlaceOrderRequest;
import com.semicolon.africa.DTOs.request.UpdatePlaceOrderRequest;
import com.semicolon.africa.DTOs.response.DeletePlacedOrderResponse;
import com.semicolon.africa.DTOs.response.PlaceOrderResponse;
import com.semicolon.africa.DTOs.response.UpdatePlaceOrderResponse;
import com.semicolon.africa.data.model.Customer;
import com.semicolon.africa.data.model.OrderPlacement;
import com.semicolon.africa.data.repository.CustomerRepository;
import com.semicolon.africa.data.repository.OrderRepository;
import com.semicolon.africa.exception.CustomerNotFoundException;
import com.semicolon.africa.exception.OrderIdNotFoundException;
import com.semicolon.africa.exception.OrderNotFoundException;
import com.semicolon.africa.service.interfaces.OrderPlacementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import static com.semicolon.africa.utils.Mapper.placeOrderResponseMapper;

@Service
@RequiredArgsConstructor
public class OrderPlacementServiceImpl implements OrderPlacementService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {
        Customer customer = findCustomerById(placeOrderRequest.getCustomerId());
        customerRepository.save(customer);
        OrderPlacement orderPlacement = new OrderPlacement();
        orderPlacement.setServiceType(placeOrderRequest.getServiceType());
        orderPlacement.setItems(placeOrderRequest.getItems());
        orderPlacement.setPrice(placeOrderRequest.getPrice());
        orderPlacement.setQuantity(placeOrderRequest.getQuantity());
        orderPlacement.setDateOrdered(placeOrderRequest.getDateOrdered());
        orderRepository.save(orderPlacement);
        return placeOrderResponseMapper(orderPlacement);
    }

    private Customer findCustomerById(Long customerId) {
        return customerRepository.findCustomerById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Customer not found"));
    }

    @Override
    public UpdatePlaceOrderResponse updateOrder(UpdatePlaceOrderRequest placeOrderRequest) {
        OrderPlacement orderPlacement = findOrderPlacementById(placeOrderRequest.getOrderId());
        orderPlacement.setItems(placeOrderRequest.getItems());
        orderPlacement.setServiceType(placeOrderRequest.getServiceType());
        orderPlacement.setPrice(placeOrderRequest.getPrice());
        orderPlacement.setQuantity(placeOrderRequest.getQuantity());
        orderPlacement.setDateUpdated(placeOrderRequest.getDateUpdated());
        orderRepository.save(orderPlacement);
        return updateOrderResponseMapper(orderPlacement);
    }
    private static UpdatePlaceOrderResponse updateOrderResponseMapper(OrderPlacement orderPlacement) {
        UpdatePlaceOrderResponse updatePlaceOrderResponse = new UpdatePlaceOrderResponse();
        updatePlaceOrderResponse.setOrderId(orderPlacement.getId());
        updatePlaceOrderResponse.setItems(orderPlacement.getItems());
        updatePlaceOrderResponse.setServiceType(orderPlacement.getServiceType());
        updatePlaceOrderResponse.setPrice(orderPlacement.getPrice());
        updatePlaceOrderResponse.setQuantity(orderPlacement.getQuantity());
        updatePlaceOrderResponse.setDateUpdated(orderPlacement.getDateUpdated());
        updatePlaceOrderResponse.setMessage("Order updated successfully");
        return updatePlaceOrderResponse;
    }

    private OrderPlacement findOrderPlacementById(Long orderId) {
        return orderRepository.findById(orderId).
                orElseThrow(()-> new OrderIdNotFoundException("Order not found "));
    }

    @Override
    public DeletePlacedOrderResponse deleteOrder(Long id) {
        OrderPlacement orderPlacement = findOrderPlacementById(id);
        orderRepository.delete(orderPlacement);
        DeletePlacedOrderResponse response = new DeletePlacedOrderResponse();
        response.setMessage("Order deleted successfully");
        return response;
    }
    @Override
    public Long countAllOrderPlaced() {
        return orderRepository.count();
    }
    @Override
    public List<OrderPlacement> allOrderPlaced() {
        return orderRepository.findAll();
    }
    @Override
    public Long getOrderById(Long id) {
        return orderRepository.findOrderPlacementById(id)
                .orElseThrow(()-> new OrderNotFoundException("No such placement"));
    }
}
