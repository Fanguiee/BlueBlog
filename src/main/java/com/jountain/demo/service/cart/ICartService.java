package com.jountain.demo.service.cart;

import com.jountain.demo.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    public Cart getCart(Long id);
    public void clearCart(Long id);
    public BigDecimal getTotalAmount(Long id);
}
