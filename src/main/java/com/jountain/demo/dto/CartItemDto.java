package com.jountain.demo.dto;

import com.jountain.demo.model.Product;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartItemDto {
    private Long id;
    private ProductDto product;
    private int quantity;
    private BigDecimal unitPrice;
}
