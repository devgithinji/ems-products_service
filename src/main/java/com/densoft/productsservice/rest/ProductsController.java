package com.densoft.productsservice.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @PostMapping
    public String createProduct() {
        return "Http post handled";
    }

    @GetMapping
    public String getProduct() {
        return "Http get handled";
    }

    @PutMapping
    public String updateProduct() {
        return "Http put handled";
    }

    @DeleteMapping
    public String deleteProduct() {
        return "Http delete handled";
    }
}
