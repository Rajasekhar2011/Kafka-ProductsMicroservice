package com.learning.sergey.kafka.products.controller;

import com.learning.sergey.kafka.products.exception.ErrorMessage;
import com.learning.sergey.kafka.products.request.CreateProductRequest;
import com.learning.sergey.kafka.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductService productService;
    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody CreateProductRequest request){
        String productId = null;
        try {
            productId = productService.createProduct(request);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(new Date(), e.getMessage(), "/products"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
