package com.jountain.demo.service.cart;

import com.jountain.demo.model.Cart;
import com.jountain.demo.model.CartItem;

import java.math.BigDecimal;
import java.util.Set;

public interface ICartItemService {
    public void addItemToCart(Long cartId,Long productId, int quantity);
    public void removeItemFromCart(Long cartId,Long productId);
    public void updateItemQuantity(Long cartId,Long productId, int quantity);

    CartItem getItemInCart(Long cartId, Long productId);
}
