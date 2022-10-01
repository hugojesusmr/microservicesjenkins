package com.microservice.inventoryservice.service;

import java.util.List;

import com.microservice.inventoryservice.dto.InventoryResponse;
import com.microservice.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    
    
    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Checking inventory");
        return inventoryRepository
                                .findBySkuCodeIn(skuCode)
                                .stream()
                                .map(i -> InventoryResponse
                                    .builder()
                                    .skuCode(i.getSkuCode())
                                    .isInStock(i.getQuantity() > 0)
                                    .build()
                                    ).toList();    
         
    }


    
}
