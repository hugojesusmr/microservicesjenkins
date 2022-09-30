package com.microservice.productservice.repository;

import com.microservice.productservice.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProductRepository extends MongoRepository<Product, String>{
    @Query(value="{'name':?0}")
    public Product findByName(String name);
}
