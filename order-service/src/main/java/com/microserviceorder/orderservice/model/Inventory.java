package com.microserviceorder.orderservice.model;

import lombok.Data;

@Data
public class Inventory {
    private Long id;
    private String skuCode;
    private Integer stock;
}
