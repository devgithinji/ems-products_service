package com.densoft.productsservice.query;

import com.densoft.productsservice.core.data.ProductEntity;
import com.densoft.productsservice.core.data.ProductsRepository;
import com.densoft.productsservice.query.rest.ProductRestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductsQueryHandler {

    private final ProductsRepository productsRepository;

    @QueryHandler
    public List<ProductRestModel> findProducts(FindProductsQuery findProductsQuery) {
        List<ProductRestModel> productRestModels = new ArrayList<>();
        List<ProductEntity> productEntities = productsRepository.findAll();
        for (ProductEntity productEntity : productEntities) {
            ProductRestModel productRestModel = new ProductRestModel();
            BeanUtils.copyProperties(productEntity, productRestModel);
            productRestModels.add(productRestModel);
        }
        return productRestModels;
    }
}
