package com.learning.sergey.kafka.products.service;

import com.learning.sergey.kafka.products.request.CreateProductRequest;

import java.util.concurrent.ExecutionException;

public interface ProductService {
    String createProduct(CreateProductRequest request) throws ExecutionException, InterruptedException;
}
