package com.microservice.productservice.service;

import java.util.List;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.model.Product;

public interface ProductService {
    public List<ProductResponse> getAllProducts();
    public Product getProduct(String name);
    public Product createProduct(ProductRequest productRequest);
    public Product updateProduct(ProductRequest productRequest);
    public void deleteProduct(String id);
}
