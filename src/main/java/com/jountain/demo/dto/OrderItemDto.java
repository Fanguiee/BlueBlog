package com.jountain.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDto {
//    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private String brand;
    private BigDecimal price;
}
