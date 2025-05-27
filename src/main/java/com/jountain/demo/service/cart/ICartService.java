package com.jountain.demo.service.cart;

import com.jountain.demo.model.Cart;
import com.jountain.demo.model.Order;
import com.jountain.demo.model.User;

import java.math.BigDecimal;

public interface ICartService {
    public Cart getCart(Long id);
    public void clearCart(Long id);
    public BigDecimal getTotalPrice(Long id);

    Cart getCartByUserId(Long userId);

    Cart initializeNewCart(User user);
}
