package com.example.springbootproject;

import com.example.springbootproject.controller.LoginController;
import com.example.springbootproject.intercreptor.LoginIntercreptor;
import com.example.springbootproject.intercreptor.RootIntercreptor;
import com.example.springbootproject.intercreptor.StudentIntercreptor;
import com.example.springbootproject.intercreptor.TeacherIntercreptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginIntercreptor loginIntercreptor;
    @Autowired
    private StudentIntercreptor studentIntercreptor;
    @Autowired
    private TeacherIntercreptor teacherIntercreptor;
    @Autowired
    private RootIntercreptor rootIntercreptor;
  //拦截器是有顺序的,从上到下
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercreptor)
                 .addPathPatterns("/api/**")
                .excludePathPatterns("/api/studentLogin")
                 .excludePathPatterns("/api/teacherLogin");
                  // .excludePathPatterns("/api/login");


        registry.addInterceptor(studentIntercreptor)
                .addPathPatterns("/api/student/**");

        registry.addInterceptor(teacherIntercreptor)
                 .addPathPatterns("/api/teacher/**");

        registry.addInterceptor(rootIntercreptor)
                 .addPathPatterns("/api/root/**");

    }
}
