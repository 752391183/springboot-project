package com.example.springbootproject.component;

import com.example.springbootproject.entity.Role;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/*
  拿到请求身上用户真实的id,role
 */
@Component
public class RequestComponent {
    public Integer getid(){
        //拿到当前线程的所有请求
        return (Integer) RequestContextHolder.currentRequestAttributes()
                .getAttribute(MyToken.UID, RequestAttributes.SCOPE_REQUEST);
    }

    public Role getRole() {
        return (Role) RequestContextHolder.currentRequestAttributes()
                .getAttribute(MyToken.ROLE,RequestAttributes.SCOPE_REQUEST);
    }

}
