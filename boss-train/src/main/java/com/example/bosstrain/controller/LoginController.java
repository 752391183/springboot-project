package com.example.bosstrain.controller;

import com.example.bosstrain.component.EncryptComponent;
import com.example.bosstrain.component.MyToken;
import com.example.bosstrain.entity.Role;
import com.example.bosstrain.entity.User;
import com.example.bosstrain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EncryptComponent encrypt;
    @Value("${my.buyer}")
    private String roleBuyer;
    @Value("${my.seller}")
    private String rollSeller;

    @PostMapping("loggin")
    public Map login(@RequestBody User login, HttpServletResponse response) {
        User user = Optional.ofNullable(userService.getUserByNumber(login.getNumber()))
                .filter(u -> encoder.matches(login.getPassWord(),u.getPassWord()))
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"账号或者密码错误"));
        MyToken myToken = new MyToken(user.getId(),user.getRole());
        String auth = encrypt.encryptToken(myToken);
        response.setHeader(MyToken.AUTHORIZATION,auth);
        String role = user.getRole() == Role.BUYER ? roleBuyer : rollSeller;
        return Map.of("role",role);
    }
}

