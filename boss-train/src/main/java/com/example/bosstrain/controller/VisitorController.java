package com.example.bosstrain.controller;

import com.example.bosstrain.component.RequestComponent;
import com.example.bosstrain.entity.Cart;
import com.example.bosstrain.entity.OrderItem;
import com.example.bosstrain.entity.Result;
import com.example.bosstrain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/*
  游客并没有登录，所以没办法拿到id,得通过cookie拿
 */
@RestController
@RequestMapping("/api/visitor/")
@Validated
public class VisitorController {
     @Autowired
     private RequestComponent requestComponent;
     @Autowired
     private UserService userService;
     private int uid; //模拟cookie中的id信息

     @GetMapping("carts")
     public Map listCarts() {
         List<Cart> list = userService.getAllCart(uid);
         return Map.of("carts",list);
     }

     @PostMapping("add/{cid}")
     public Map addCart(@RequestBody OrderItem orderItem,@PathVariable Integer cid) {
        List<Cart> list =  userService.addOrderItem(orderItem,cid,uid);
         return Map.of("carts",list);
     }

}
