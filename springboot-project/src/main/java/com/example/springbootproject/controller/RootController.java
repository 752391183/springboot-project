package com.example.springbootproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
  只允许管理员进行的访问
 */
@RestController
@RequestMapping("/api/root/")
public class RootController {

}
