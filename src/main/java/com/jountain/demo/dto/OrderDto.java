package com.jountain.demo.dto;

import com.jountain.demo.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private LocalDate date;
    private BigDecimal totalAmount;

    private OrderStatus status;
    private List<OrderItemDtos> items;
}
