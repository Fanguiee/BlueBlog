package com.jountain.demo.service.order;

import com.jountain.demo.dto.OrderDto;
import com.jountain.demo.dto.OrderItemDto;
import com.jountain.demo.enums.OrderStatus;
import com.jountain.demo.exceptions.ResourceNotFoundException;
import com.jountain.demo.model.Cart;
import com.jountain.demo.model.Order;
import com.jountain.demo.model.OrderItem;
import com.jountain.demo.model.Product;
import com.jountain.demo.repository.OrderRepository;
import com.jountain.demo.repository.ProductRepository;
import com.jountain.demo.service.cart.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public Order placeOrder(Long userId) {
        Cart cart=cartService.getCartByUserId(userId);
        Order order=createOrder(cart);
        List<OrderItem> orderItems=createOrderItems(order,cart);
        order.setItems(new HashSet<>(orderItems));
        order.setTotalAmount(calculateTotalPrice(orderItems));
        return orderRepository.save(order);
    }


    private Order createOrder(Cart cart){
        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDING);
        order.setUser(cart.getUser());
        Order savedOrder = orderRepository.save(order);

        cartService.clearCart(cart.getId());
        return savedOrder;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart){
        return cart.getItems()
                .stream()
                .map((item)->{
                    Product product=item.getProduct();
                    product.setInventory(product.getInventory()-item.getQuantity());
                    productRepository.save(product);
                    return new OrderItem(order,product,item.getQuantity(),product.getPrice());
                })
                .toList();
    }


    private BigDecimal calculateTotalPrice(List<OrderItem> items){
        return items.stream()
                .map((item)->item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this::convertOrderToDto)
                .orElseThrow(()->new ResourceNotFoundException("No Order found"));
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId){
        return orderRepository.findByUserId(userId).stream().map(this::convertOrderToDto).toList();
    }

    public OrderDto convertOrderToDto(Order order){
        return modelMapper.map(order, OrderDto.class);
    }
}
