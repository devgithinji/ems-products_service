package com.densoft.productsservice.command;

import com.densoft.productsservice.core.data.ProductLookupEntity;
import com.densoft.productsservice.core.data.ProductLookupRepository;
import com.densoft.productsservice.core.events.ProductCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
@RequiredArgsConstructor
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(productCreatedEvent.getProductId(), productCreatedEvent.getTitle());
        productLookupRepository.save(productLookupEntity);
    }
}
