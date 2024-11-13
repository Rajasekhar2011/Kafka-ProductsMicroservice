package com.learning.sergey.kafka.products.service;

import com.learning.sergey.kafka.core.ProductCreatedEvent;
import com.learning.sergey.kafka.products.request.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    @Override
    public String createProduct(CreateProductRequest request) throws ExecutionException, InterruptedException {
        String productId = UUID.randomUUID().toString();
        //TODO -> persist data into DB before sending the message to kafka broker.
        ProductCreatedEvent event = new ProductCreatedEvent(productId, request.getTitle(), request.getPrice(), request.getQuantity());
        System.out.println("Before publishing an event");
        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-events-topic",productId, event ).get();
        System.out.println("Partition -> "+result.getRecordMetadata().partition());
        System.out.println("Offset -> "+result.getRecordMetadata().offset());

        System.out.println("**** return product id -> "+productId+"*****");
        return productId;
    }
}
