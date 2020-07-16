package com.example.bosstrain.repository;

import com.example.bosstrain.entity.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends BaseRepository<Cart, Integer> {
}
