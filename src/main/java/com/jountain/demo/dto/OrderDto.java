package com.jountain.demo.dto;

import com.jountain.demo.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long userId;
    private LocalDateTime time;
    private BigDecimal totalAmount;

    private OrderStatus status;
    private List<OrderItemDto> items;
}
