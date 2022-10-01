package com.microservice.inventoryservice.service;

import java.util.List;

import com.microservice.inventoryservice.dto.InventoryResponse;

public interface InventoryService {  
    public List<InventoryResponse> isInStock(List<String> skuCode);
}
