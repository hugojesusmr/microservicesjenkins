package com.example.notificationservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}
    @KafkaListener(topics = "notificationTopic",groupId = "notification-Id")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent){
		log.info("Received Notification for Order - {}", orderPlacedEvent.getOrderNumber());
	}
}
