package com.example.cart_demo.dto;

import com.example.cart_demo.model.Product;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@SessionScope
@Getter
public class CartDTO {
    private Map<Long, Integer> products = new LinkedHashMap<>();

    public void addProduct(Long productId){
       products.merge(productId,1,Integer::sum);
    }

    public void decreaseProduct(Long productId)
    {
        products.compute(productId,(key, currentQuantity) -> (currentQuantity == 1) ? null : currentQuantity - 1);
    }

    public void removeProduct(Long productId)
    {
        products.remove(productId);
    }
}
