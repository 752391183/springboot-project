package com.example.bosstrain.service;

import com.example.bosstrain.entity.Cart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartService {

     public List<Cart> findCartList() {
         return null;
     }
}
