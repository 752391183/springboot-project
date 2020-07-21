package com.example.bosstrain.entity;


import com.example.bosstrain.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CartTest {
    @Autowired
    private UserService userService;

    @Test
    public void test_init() {
        Cart c1 = new Cart();
        c1.setSellerName("Cart1");
        userService.saveCart(c1);
        Cart c2 = new Cart();
        c2.setSellerName("Cart2");
        userService.saveCart(c2);

        OrderItem o1 = new OrderItem("Oder1", 12.00, 2);
        userService.saveOrderItem(o1,c1);
        OrderItem o2 = new OrderItem("Oder2", 10.00, 1);
        userService.saveOrderItem(o2,c1);
        OrderItem o3 = new OrderItem("Oder3", 15.00, 3);
        userService.saveOrderItem(o3,c1);
        OrderItem o4 = new OrderItem("Oder4", 13.00, 5);
        userService.saveOrderItem(o4,c2);
        OrderItem o5 = new OrderItem("Oder5", 15.00, 6);
        userService.saveOrderItem(o5,c2);
    }

}
