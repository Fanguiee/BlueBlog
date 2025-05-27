package com.jountain.demo.dto;

import com.jountain.demo.model.Product;

import java.math.BigDecimal;

public class CartItemDto {
    private Long id;
    private ProductDto product;
    private int quantity;
    private BigDecimal unitPrice;
}
