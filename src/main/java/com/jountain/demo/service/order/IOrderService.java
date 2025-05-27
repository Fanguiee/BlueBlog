package com.jountain.demo.service.order;

import com.jountain.demo.dto.OrderDto;
import com.jountain.demo.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
