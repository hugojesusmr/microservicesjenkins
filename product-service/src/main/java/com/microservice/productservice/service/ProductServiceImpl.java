package com.microservice.productservice.service;

import java.util.List;
import java.util.stream.Collectors;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.dto.ProductResponse;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllProducts() {
       List<Product> products = productRepository.findAll();
       return products
                .stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse
               .builder()
               .name(product.getName())
               .description(product.getDescription())
               .price(product.getPrice())
               .build();
    }

    @Override
    public Product getProduct(String name) {
        return productRepository.findByName(name);
    }
    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product = Product
        .builder()
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .build();
        log.info("Product {} is saved", product.getId());
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(ProductRequest productRequest) {
        Product productDB = getProduct(productRequest.getId());
                
        productDB.setName(productRequest.getName());
        productDB.setDescription(productRequest.getDescription());
        productDB.setPrice(productRequest.getPrice());
        return productRepository.save(productDB);
 
    }
    @Override
    public void deleteProduct(String id) {
        Product productDB = getProduct(id);
        productRepository.save(productDB);
    }
}
