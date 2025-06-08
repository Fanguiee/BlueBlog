package com.jountain.demo.controller;

import com.jountain.demo.exceptions.ResourceNotFoundException;
import com.jountain.demo.model.Cart;
import com.jountain.demo.repository.CartRepository;
import com.jountain.demo.response.ApiResponse;
import com.jountain.demo.service.cart.CartItemService;
import com.jountain.demo.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/carts")
@RequiredArgsConstructor
public class CartController {
    private final ICartService cartService;
    private final CartRepository cartRepository;

    @GetMapping("/{cartId}/cart")
    public ResponseEntity<ApiResponse> getCart(@PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Get Cart Success",cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getCartByUserId(@PathVariable Long userId) {
        try {
            Cart cart = cartRepository.findByUserId(userId);
            return ResponseEntity.ok(new ApiResponse("Get Cart by user id Success",cart));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }


    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok(new ApiResponse("Clear Cart Success",null));
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getCartTotalPrice(@PathVariable Long cartId) {
        try {
            BigDecimal totalPrice = cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Get Total Price",totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

}
