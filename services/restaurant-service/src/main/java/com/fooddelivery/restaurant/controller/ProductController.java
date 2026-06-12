package com.fooddelivery.restaurant.controller;

import com.fooddelivery.restaurant.dto.CreateProductRequest;
import com.fooddelivery.restaurant.dto.ProductResponse;
import com.fooddelivery.restaurant.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @PostMapping
    public ProductResponse createProduct(
            @Valid
            @RequestBody
            CreateProductRequest request
    ) {

        return productService
                .createProduct(request);
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductResponse>
    getProducts(
            @PathVariable Long categoryId
    ) {

        return productService
                .getProductsByCategory(
                        categoryId
                );
    }

    @GetMapping(
            "/category/{categoryId}/available"
    )
    public List<ProductResponse>
    getAvailableProducts(
            @PathVariable Long categoryId
    ) {

        return productService
                .getAvailableProductsByCategory(
                        categoryId
                );
    }

    @PutMapping("/{id}/unavailable")
    public ProductResponse markProductUnavailable(@PathVariable Long id) {
        return productService.markProductUnavailable(id);
    }

    @GetMapping("/search")
    public List<ProductResponse>
    searchProducts(
            @RequestParam String name
    ) {

        return productService
                .searchProducts(name);
    }
}