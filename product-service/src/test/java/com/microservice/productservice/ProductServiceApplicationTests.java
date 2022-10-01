package com.microservice.productservice;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.productservice.dto.ProductRequest;
import com.microservice.productservice.repository.ProductRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
    
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;//convertir un objeto pojo en json y un json en un objeto pojo
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void getAllProducts()throws Exception{
		mockMvc
		.perform(MockMvcRequestBuilders
		.get("/api/product")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andReturn();
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String producRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc
		      .perform(MockMvcRequestBuilders
	  	      .post("/api/product")
			  .contentType(MediaType.APPLICATION_JSON)
			  .content(producRequestString)
			  .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isCreated())
			  .andReturn();
		Assertions.assertEquals(3,productRepository.findAll().size());		
	}

	private ProductRequest getProductRequest() {
		return ProductRequest
						.builder()
						.name("N0K14")
						.description("N0K14")
						.price(BigDecimal.valueOf(11111))
						.build();
	}

}
