package com.microservice.productservice.controller;

import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.model.Product;
import com.microservice.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/product")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> listProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK); 
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<?> getProduct(@PathVariable String name){
        return new ResponseEntity<>(productService.getProduct(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        Product product = productService.createProduct(productRequest);
         return ResponseEntity.status(HttpStatus.CREATED).body(product);
        
    }
}
