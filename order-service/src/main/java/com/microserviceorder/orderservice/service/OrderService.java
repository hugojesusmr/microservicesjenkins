package com.microserviceorder.orderservice.service;

import com.microserviceorder.orderservice.dto.OrderRequest;
import com.microserviceorder.orderservice.entity.Order;

public interface OrderService {
    public Order placeOrder(OrderRequest orderDtoRequest);
   
}
