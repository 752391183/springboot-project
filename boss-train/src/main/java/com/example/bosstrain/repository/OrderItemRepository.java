package com.example.bosstrain.repository;

import com.example.bosstrain.entity.OrderItem;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends BaseRepository<OrderItem,Integer> {

}
