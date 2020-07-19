package com.example.bosstrain.service;

import com.example.bosstrain.entity.Cart;
import com.example.bosstrain.entity.OrderItem;
import com.example.bosstrain.repository.CartRepository;
import com.example.bosstrain.repository.OrderItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@Slf4j
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    //模拟数据
 /*   public Map getInit() {

        Cart c1 = new Cart();
        c1.setSellerName("Cart1");
        addCart(c1);
        Cart c2 = new Cart();
        c2.setSellerName("Cart2");
        addCart(c2);

        OrderItem o1 = new OrderItem("Oder1", 12.00, 2);
        addOrderItem(o1,c1);
        OrderItem o2 = new OrderItem("Oder2", 10.00, 1);
        addOrderItem(o2,c1);
        OrderItem o3 = new OrderItem("Oder3", 15.00, 3);
        addOrderItem(o3,c1);
        OrderItem o4 = new OrderItem("Oder4", 13.00, 5);
        addOrderItem(o4,c2);
        OrderItem o5 = new OrderItem("Oder5", 15.00, 6);
        addOrderItem(o5,c2);

        List<OrderItem> list1 = new ArrayList<>();
        list1.add(o1);
        list1.add(o2);
        list1.add(o3);
        List<OrderItem> list2 = new ArrayList<>();
        list2.add(o4);
        list2.add(o5);

        Map<String, List<OrderItem>> map = new HashMap<>();
        map.put("Cart1", list1);
        map.put("Cart2", list2);

        Set<String> keys = map.keySet();
        for (String s : keys) {
            List<OrderItem> list = map.get(s);
            for (OrderItem o : list) {
                log.debug("{}",o.getPrice());
            }
        }
        return map;
    }*/
    //通过主键查找对应Cart
    public Cart getCartById(Integer id) {
        return cartRepository.findCartById(id);
    }

    //通过名称查找对应Cart
    public List<Cart> getCartByName(String name) {
        return cartRepository.findCartByName(name).orElse(null);
    }

    //添加Cart
    public void addCart(Cart cart) {
        cartRepository.save(cart);
    }

    //添加OrderItem
    public void addOrderItem(OrderItem orderItem,Cart cart) {
        orderItem.setCart(cart);
        orderItemRepository.save(orderItem);
    }

    //删除Cart
    public void deleteCartById(Integer id) {
        cartRepository.deleteById(id);
    }

    //给指定的Cart添加商品
    public Cart addOrderItemByCartId(OrderItem orderItem,Integer id) {
            return null;
    }
}
