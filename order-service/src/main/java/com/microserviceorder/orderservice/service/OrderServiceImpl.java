package com.microserviceorder.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.microserviceorder.orderservice.dto.OrderRequest;
import com.microserviceorder.orderservice.entity.Order;
import com.microserviceorder.orderservice.entity.OrderLineItems;
import com.microserviceorder.orderservice.event.OrderPlacedEvent;
import com.microserviceorder.orderservice.dto.InventoryResponse;
import com.microserviceorder.orderservice.dto.OrderLineItemsDto;
import com.microserviceorder.orderservice.repositoty.OrderRepository;

import brave.Span;
import brave.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;
    private final Tracer tracer;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;


    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());        
                
        List<OrderLineItems> orderLineItems =
        orderRequest.getOrderLineItemsDtoList()
                    .stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
       
       order.setOrderLineItemsList(orderLineItems);        
       System.out.println(order.toString());
       List<String> skuCodes = 
       order.getOrderLineItemsList()
            .stream()
            .map(OrderLineItems::getSkuCode)
            .collect(Collectors.toList());

        //Call Inventory Service
        log.info("Calling inventory service");
        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");
        try(Tracer.SpanInScope isLookup = tracer.withSpanInScope(inventoryServiceLookup.start())){
          
          InventoryResponse[] inventoryResponses = webClient
                   .build()
                   .get()
                   .uri("http://localhost:8005/api/inventory",
                        uriBuider->uriBuider.queryParam("skuCode", skuCodes).build())
                   .retrieve()
                   .bodyToMono(InventoryResponse[].class)
                   .block();
           
           boolean allProductsInStock = Arrays
                                       .stream(inventoryResponses)
                                       .allMatch(InventoryResponse::isInStock);                       
   
            if (allProductsInStock) {
             kafkaTemplate.send("notificationTopic", new OrderPlacedEvent(order.getOrderNumber())); 
             return orderRepository.save(order);
           } else {
             throw new IllegalArgumentException("Product is not in stock, please try again later");
           }                        
        } finally {
          inventoryServiceLookup.flush();
        }

    }
    
  private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
     OrderLineItems orderLineItems = new OrderLineItems();
            orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
            orderLineItems.setPrice(orderLineItemsDto.getPrice());
            orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
  }
}
