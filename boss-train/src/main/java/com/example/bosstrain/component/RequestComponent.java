package com.example.bosstrain.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class RequestComponent {
    public Integer getid(){
        //拿到当前线程的所有请求
        return (Integer) RequestContextHolder.currentRequestAttributes()
                .getAttribute(MyToken.UID, RequestAttributes.SCOPE_REQUEST);
    }
}
