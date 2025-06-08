package com.jountain.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private int quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Order order, Product product,int quantity, BigDecimal price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
