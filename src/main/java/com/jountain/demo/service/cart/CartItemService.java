package com.jountain.demo.service.cart;

import com.jountain.demo.exceptions.InsufficientInventoryException;
import com.jountain.demo.model.Cart;
import com.jountain.demo.model.CartItem;
import com.jountain.demo.model.Product;
import com.jountain.demo.repository.CartItemRepository;
import com.jountain.demo.repository.CartRepository;
import com.jountain.demo.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {
    private final CartItemRepository cartItemRepository;
    private final ICartService cartService;
    private final IProductService productService;
    private final CartRepository cartRepository;

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        //1. check product exists
        //2. check product inventory
        //3.look for the product in the cart
        //if product not in the cart, add it
        //if product already in the cart, update the quantity and totalPrice
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        int inventory = product.getInventory();
        if(inventory < quantity) {
            throw new InsufficientInventoryException("Insufficient inventory");
        }
        CartItem cartItem = getItemInCart(cartId, productId);
        if (cartItem== null) {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cartItem.setUnitPrice(product.getPrice());
        }else {
            cartItem.setQuantity(quantity+cartItem.getQuantity());
        }
        cartItem.setTotalPrice();
        cartItemRepository.save(cartItem);
        cart.addItem(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem cartItemToRemove= getItemInCart(cartId,productId);
        cart.removeItem(cartItemToRemove);
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        cart.getItems()
                .stream()
                .filter(item->item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item->{
                    item.setQuantity(quantity);
                    item.setTotalPrice();
                    cartItemRepository.save(item);
                });
        cart.updateTotalAmount();
        cartRepository.save(cart);
    }

    @Override
    public CartItem getItemInCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        return cart.getItems()
                .stream()
                .filter(item->item.getProduct().getId().equals(productId))
                .findFirst().orElse(null);

    }
}
