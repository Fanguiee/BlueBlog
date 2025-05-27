package com.jountain.demo.dto;

import com.jountain.demo.model.CartItem;

import java.math.BigDecimal;
import java.util.Set;

public class CartDto {
    private Long id;
    private Set<CartItemDto> items;
    private BigDecimal totalAmount;
}
