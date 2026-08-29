package com.example.cart_demo.service;

import com.example.cart_demo.model.Order;
import com.example.cart_demo.model.OrderDetail;
import com.example.cart_demo.model.Product;
import com.example.cart_demo.repository.OrderDetailRepository;
import com.example.cart_demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    @Transactional
    public void placeOrder(Order order, Map<Product, Integer> cartItems) {
        // 1. Set ngày đặt hàng
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(savedOrder);
            detail.setProduct(entry.getKey());
            detail.setQuantity(entry.getValue());
            detail.setPrice(entry.getKey().getPrice());
            
            orderDetailRepository.save(detail);
        }
    }
}
