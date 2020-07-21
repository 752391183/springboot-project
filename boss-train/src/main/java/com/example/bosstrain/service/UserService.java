package com.example.bosstrain.service;

import com.example.bosstrain.entity.Cart;
import com.example.bosstrain.entity.OrderItem;
import com.example.bosstrain.entity.Result;
import com.example.bosstrain.entity.User;
import com.example.bosstrain.repository.CartRepository;
import com.example.bosstrain.repository.OrderItemRepository;
import com.example.bosstrain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    //根据id查询用户
    public User getUserById(Integer uid) {
        User u = userRepository.findById(uid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "没有对应的用户"));
        return u;
    }

    //根据number查询用户
    public User getUserByNumber(Integer num) {
        User u = userRepository.findUserByNumber(num).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "没有对应的用户"));
        return u;
    }

    //通过主键查找对应Cart
    public Cart getCartById(Integer cid) {
        return cartRepository.findById(cid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "没有对应的购物车"));
    }

    //通过名称查找对应Cart
    public List<Cart> getCartByName(String name) {
        return cartRepository.findCartByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "没有对应的购物车"));
    }

    //保存Cart
    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    //保存OrderItem
    public void saveOrderItem(OrderItem orderItem, Cart cart) {
        orderItem.setCart(cart);
        orderItemRepository.save(orderItem);
    }

    //删除Cart
    public void deleteCartById(Integer cid) {
        cartRepository.deleteById(cid);
    }

    //
    public void deleteOrderItem(Integer oid) {
        orderItemRepository.deleteById(oid);
    }

    //列出该用户所有的商品
    public List<Cart> getAllCart(Integer uid) {
        List<Cart> carts = getUserById(uid).getCarts();
        if (carts == null || carts.isEmpty()) {
            return List.of();
        }
        return carts;
    }

    //列出指定Cart的购物清单
    public List<OrderItem> getAllOrderItem(Integer cid) {
        List<OrderItem> orderItems = getCartById(cid).getOrderItemList();
        if (orderItems == null || orderItems.isEmpty()) {
            return List.of();
        }
        return orderItems;
    }

    //添加商品
    public List<Cart> addOrderItem(OrderItem orderItem, Integer cid, Integer uid) {
        List<Cart> carts = getAllCart(uid);
        List<OrderItem> orderItems = getAllOrderItem(cid);
        for (Cart c : carts) {
            if (c.getSellerId().equals(cid)) {
                for (OrderItem o : orderItems) {
                    if (o.getId().equals(orderItem.getId())) {
                        o.setCount(o.getCount() + orderItem.getCount());
                        return carts;
                    }
                }
                orderItems.add(orderItem);
                return carts;
            }
        }
        Cart c = getCartById(cid);
        c.getOrderItemList().add(orderItem);
        carts.add(c);
        return carts;
    }

    //获取商品支付价格
    public double getSumPrice(Integer uid) {
        User u = getUserById(uid);
        double sum = 0;
        for (Cart c : u.getCarts()) {
            for (OrderItem o : c.getOrderItemList()) {
                sum = sum + o.getPrice();
            }
        }
        return sum;
    }
}
