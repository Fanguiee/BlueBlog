package com.jountain.demo.service.cart;

import com.jountain.demo.exceptions.ResourceNotFoundException;
import com.jountain.demo.model.Cart;
import com.jountain.demo.model.User;
import com.jountain.demo.repository.CartItemRepository;
import com.jountain.demo.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

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

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cart.setTotalAmount(BigDecimal.ZERO);
        cart.setUser(null);
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart initializeNewCart(User user){
        //if the user has no cart, initialize one.
        return Optional.ofNullable(getCartByUserId(user.getId()))
                .orElseGet(()->{
                    Cart cart = new Cart();
                    cart.setUser(user);
                    cart.setTotalAmount(BigDecimal.ZERO);
                    return cartRepository.save(cart);
                });
    }
}
