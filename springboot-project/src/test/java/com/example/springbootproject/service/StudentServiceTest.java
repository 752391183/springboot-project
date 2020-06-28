package com.example.springbootproject.service;

import com.example.springbootproject.entity.Direction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
@Slf4j
public class StudentServiceTest {
   @Autowired
   private StudentService studentService;
    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void test_choseDirection() {
        List<Integer> integers = List.of(1,2,3,4);
        List<Direction> directions = studentService.choseDirection(1,integers);
        directions.forEach(d -> log.debug("{}",d.getName()));
    }

    @Test
    public void test_choseTeacher() {
        studentService.choseTeacher(1,1);
    }
}
