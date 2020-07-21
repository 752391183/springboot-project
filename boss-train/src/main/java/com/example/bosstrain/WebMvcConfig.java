package com.example.bosstrain;

import com.example.bosstrain.intercreptor.BuyerIntercreptor;
import com.example.bosstrain.intercreptor.LoginIntercreptor;
import com.example.bosstrain.intercreptor.SellerIntercreptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
  当游客想要做出对购物车进行支付购买操作的时候需要进行登录，添加拦截路径
  对买家和卖家操作的访问进行拦截
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginIntercreptor loginIntercreptor;
    @Autowired
    private BuyerIntercreptor buyerIntercreptor;
    @Autowired
    private SellerIntercreptor sellerIntercreptor;
    //拦截器是有顺序的,从上到下
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercreptor)
                .addPathPatterns("/api/loggin")
                .addPathPatterns("/api/visitor/pay");

        registry.addInterceptor(buyerIntercreptor)
                 .addPathPatterns("/api/buyer/**");

        registry.addInterceptor(sellerIntercreptor)
                .addPathPatterns("/api/seller/**");
    }
}