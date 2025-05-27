package com.jountain.demo.dto;


import com.jountain.demo.model.Cart;

import java.util.List;

public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private List<OrderDto> orders;
    private CartDto cart;
}
