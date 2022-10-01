package com.microserviceorder.orderservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.microserviceorder.orderservice.dto.OrderRequest;
import com.microserviceorder.orderservice.entity.Order;
import com.microserviceorder.orderservice.service.OrderService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequest orderRequest){
        log.info("Creating order: {}", orderRequest);
        Order order =orderService.placeOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
           
    }

    public ResponseEntity<?> fallbackMethod(@RequestBody OrderRequest orderRequest, Throwable err){
        log.info("Falllback",err);
        return new ResponseEntity<>("Oops! Something went wrong, please order after some time!!",HttpStatus.OK);
        
    }
}
