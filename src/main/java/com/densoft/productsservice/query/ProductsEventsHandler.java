package com.densoft.productsservice.query;

import com.densoft.productsservice.core.data.ProductEntity;
import com.densoft.productsservice.core.data.ProductsRepository;
import com.densoft.productsservice.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("product-group")
public class ProductsEventsHandler {
    private final ProductsRepository productsRepository;

    @ExceptionHandler(resultType = IllegalStateException.class)
    public void handle(IllegalArgumentException exception) {
        //log error general message
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        //log error message
        throw exception;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productsRepository.save(productEntity);
    }

}
