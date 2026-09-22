package com.example.cart_demo.service;

import com.example.cart_demo.model.Order;
import com.example.cart_demo.model.Product;

import java.util.Map;

public interface OrderService {
    void placeOrder(Order order, Map<Product, Integer> cartItems);
}
