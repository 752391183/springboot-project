package com.example.springbootproject.controller;


import com.example.springbootproject.component.EncryptComponent;
import com.example.springbootproject.component.MyToken;
import com.example.springbootproject.entity.Role;
import com.example.springbootproject.entity.Student;
import com.example.springbootproject.entity.Teacher;
import com.example.springbootproject.service.StudentService;
import com.example.springbootproject.service.TeacherService;
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
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EncryptComponent encrypt;
    @Value("${my.student}")
    private String roleStudent;
    @Value("${my.teacher}")
    private String roleTeacher;
    @Value("${my.root}")
    private String roleRoot;

    @PostMapping("studentLogin")
    public Map login(@RequestBody Student login, HttpServletResponse response) {
        Student student = Optional.ofNullable(studentService.getStudentByNumber(login.getNumber()))
                  .filter(u -> encoder.matches(login.getPassword(),u.getPassword()))
                   .orElseThrow(() ->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"学号或者密码错误"));
        MyToken myToken = new MyToken(student.getId(),student.getRole());
        String auth = encrypt.encryptToken(myToken);
        response.setHeader(MyToken.AUTHORIZATION,auth);
        String role = roleStudent;
        return Map.of("role",role);
    }



    @PostMapping("teacherLogin")
    public Map login(@RequestBody Teacher login,HttpServletResponse response) {
        Teacher teacher = Optional.ofNullable(teacherService.getTeacherByNumber(login.getNumber()))
                .filter(u ->encoder.matches(login.getPassword(),u.getPassword()))
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"员工号或者密码错误"));

        MyToken myToken = new MyToken(teacher.getId(),teacher.getRole());
        String auth = encrypt.encryptToken(myToken);
        //这是约定，放在响应的头部，每次客户端发请求都得携带
        response.setHeader(MyToken.AUTHORIZATION,auth);
      /*
        if(teacher.getRole().equals(Role.ROOT)) {
            String role = roleRoot;
            return Map.of("role",role);
        }
        String role = roleTeacher;
        return Map.of("role",role);

       */
      String role = teacher.getRole() == Role.TEACHAER ? roleTeacher:roleRoot;
      return  Map.of("role",role);
    }
}
