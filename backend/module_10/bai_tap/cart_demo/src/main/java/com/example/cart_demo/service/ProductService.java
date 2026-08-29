package com.example.cart_demo.service;

import com.example.cart_demo.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);

    List<Product> findAllById(Set<Long> listIds);
}
