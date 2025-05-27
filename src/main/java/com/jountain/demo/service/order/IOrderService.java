package com.jountain.demo.service.order;

import com.jountain.demo.dto.OrderDto;
import com.jountain.demo.model.Cart;
import com.jountain.demo.model.Order;
import com.jountain.demo.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
