package com.densoft.productsservice.command.rest;

import com.densoft.productsservice.command.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsCommandController {

    private final Environment environment;
    private final CommandGateway commandGateway;


    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .title(createProductRestModel.getTitle())
                .quantity(createProductRestModel.getQuantity())
                .productId(UUID.randomUUID().toString())
                .build();

        return commandGateway.sendAndWait(createProductCommand);
    }

//    @GetMapping
//    public String getProduct() {
//        return "Http get handled: " + environment.getProperty("local.server.port");
//    }

//    @PutMapping
//    public String updateProduct() {
//        return "Http put handled";
//    }
//
//    @DeleteMapping
//    public String deleteProduct() {
//        return "Http delete handled";
//    }
}
