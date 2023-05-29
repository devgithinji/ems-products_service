package com.densoft.productsservice.query;

import com.densoft.productsservice.core.data.ProductEntity;
import com.densoft.productsservice.core.data.ProductsRepository;
import com.densoft.productsservice.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ProcessingGroup("product-group")
public class ProductsEventsHandler {
    private final ProductsRepository productsRepository;

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productsRepository.save(productEntity);
    }

}
