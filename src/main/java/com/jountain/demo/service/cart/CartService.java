package com.jountain.demo.service.cart;

import com.jountain.demo.exceptions.ResourceNotFoundException;
import com.jountain.demo.model.Cart;
import com.jountain.demo.model.CartItem;
import com.jountain.demo.repository.CartItemRepository;
import com.jountain.demo.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    //TODO: delete get, setTotalAmount.
    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Cart not found"));
        BigDecimal total = cart.getTotalAmount();
        cart.setTotalAmount(total);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }
}
