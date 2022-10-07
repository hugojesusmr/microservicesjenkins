package com.microservice.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.microservice.inventoryservice.model.Inventory;
import com.microservice.inventoryservice.repository.InventoryRepository;  

@SpringBootApplication
@EnableEurekaClient 
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory i = new Inventory();
			i.setSkuCode("iphone_13");
			i.setQuantity(100);
			Inventory i2 = new Inventory();
			i2.setSkuCode("iphone_13_red");
			i2.setQuantity(0);
			inventoryRepository.save(i);
			inventoryRepository.save(i2);
		};
	}
}
