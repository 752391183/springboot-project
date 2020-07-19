package com.example.bosstrain.controller;

import com.example.bosstrain.component.RequestComponent;
import com.example.bosstrain.entity.Cart;
import com.example.bosstrain.entity.OrderItem;
import com.example.bosstrain.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart/")
public class CartController {
     @Autowired
     private RequestComponent requestComponent;
     @Autowired
     private CartService cartService;

     @PostMapping("{cid}/addOrderItem")
    public Map addOrderItem(@RequestBody OrderItem orderItem,@PathVariable Integer cid) {
        return null;
     }
}
