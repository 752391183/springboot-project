package com.example.bosstrain.entity;

import com.example.bosstrain.repository.CartRepository;
import com.example.bosstrain.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
@Slf4j
public class CartTest {
   @Autowired
    private CartService cartService;

    @Test
    public void test_init() {
        Cart c1 = new Cart();
        c1.setSellerName("Cart1");
        cartService.addCart(c1);
        Cart c2 = new Cart();
        c2.setSellerName("Cart2");
        cartService.addCart(c2);

        OrderItem o1 = new OrderItem("Oder1", 12.00, 2);
        cartService.addOrderItem(o1,c1);
        OrderItem o2 = new OrderItem("Oder2", 10.00, 1);
        cartService.addOrderItem(o2,c1);
        OrderItem o3 = new OrderItem("Oder3", 15.00, 3);
        cartService.addOrderItem(o3,c1);
        OrderItem o4 = new OrderItem("Oder4", 13.00, 5);
        cartService.addOrderItem(o4,c2);
        OrderItem o5 = new OrderItem("Oder5", 15.00, 6);
        cartService.addOrderItem(o5,c2);
    }



/*    @Test
    public void test_init() {
        Cart c1 = new Cart();
        c1.setSellerName("Cart1");
        cartService.addCart(c1);

        Cart c2 = new Cart();
        c2.setSellerName("Cart2");
        cartService.addCart(c2);

        OrderItem o1 = new OrderItem("Oder1",12.00,2);
        OrderItem o2 = new OrderItem("Oder2",10.00,1);
        OrderItem o3 = new OrderItem("Oder3",15.00,3);

        List<OrderItem> list = new ArrayList<>();
        list.add(o1);
        list.add(o2);
        list.add(o3);

        Map<Integer,Cart> map = new HashMap<>();
        map.put(1,c1);
        map.put(2,c2);

    }*/
}
