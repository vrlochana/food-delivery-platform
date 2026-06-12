package com.fooddelivery.restaurant.dto;

public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private Boolean available;
    private Long categoryId;

    public ProductResponse(Long id, String name, Double price, Boolean available, Long categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
        this.categoryId = categoryId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}