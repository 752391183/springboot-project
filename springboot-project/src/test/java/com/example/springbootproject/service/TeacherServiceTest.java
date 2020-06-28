package com.example.springbootproject.service;

import com.example.springbootproject.entity.Course;
import com.example.springbootproject.entity.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
public class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void test_advancedStudent() {
        teacherService.AdvanceElected(2017214216,1);
    }

    @Test
    public void  test_updatePassword() {
        teacherService.updatePassword(1,"1002");
    }

    @Test
    public void test_updateTeacher() {
        Teacher t = new Teacher();
        t.setNumber(123456);
        t.setPassword(encoder.encode("123456"));
        t.setHaveSelectedNumber(0);
        t.setSelectNumber(20);
        t.setRanges(20);
        t.setName("XXX");
        teacherService.updateTeacher(t,1);
    }

    @Test
    public void test_getTeacher() {
        Teacher t = teacherService.getTeacherByNumber(1002);
        log.debug("{}",t.getName());
    }

    @Test
    public void test_addTeacher() {
        Course c = new Course();
        c.setName("JAVA");
        c.setValue(3.0F);
        teacherService.addCourse(c,1);
    }

    @Test
    public void test_deleteTeacher() {
        teacherService.deleteTeacher(1);
    }

}
