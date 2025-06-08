package com.jountain.demo.dto;

import com.jountain.demo.model.CartItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
@Data
public class CartDto {
    private Long id;
    private Set<CartItemDto> items;
    private BigDecimal totalAmount;
}
