package com.example.bosstrain.repository;

import com.example.bosstrain.entity.Cart;
import com.example.bosstrain.entity.OrderItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends BaseRepository<Cart, Integer> {

   /* @Query("select c.orderItemList from Cart c where c.sellerId =:id")
    Optional<List<OrderItem>> findAllOrderItemListByCartId (@Param("id") Integer id);*/

    @Query("from Cart c where c.sellerId =:id")
    Cart findCartById(@Param("id") Integer id);

    @Query("from Cart c where c.sellerName =:name")
    Optional<List<Cart>> findCartByName(@Param("name") String name);

}
