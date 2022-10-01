/* package com.microserviceorder.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microserviceorder.orderservice.model.Inventory;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    
    @GetMapping(value = "/api/inventory/{skuCode}")
    public ResponseEntity<Inventory> getCheckStock(@RequestParam(name="skuCode") String skuCode);
} */
