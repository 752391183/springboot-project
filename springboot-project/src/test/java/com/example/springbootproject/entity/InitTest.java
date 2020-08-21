package com.example.springbootproject.entity;

import com.example.springbootproject.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


@Slf4j
@SpringBootTest
public class InitTest  {
    @Autowired
    private DirectionBaseRepository directionBaseRepository;
    @Autowired
    private TeacherDirectionBaseRepository teacherDirectionBaseRepository;
    @Autowired
    private CourseBaseRepository courseBaseRepository;
    @Autowired
    private StudentDirectionBaseRepository studentDirectionBaseRepository;
    @Autowired
    private StudentCourseBaseRepository studentCourseBaseRepository;
    @Autowired
    private StudentBaseRepository studentBaseRepository;
    @Autowired
    private TeacherBaseRepository teacherBaseRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void test_init(){
        Teacher teacher = new Teacher();
        teacher.setNumber(1003);
        teacher.setName("WANG");
        teacher.setPassword("123456");
        teacher.setRanges(20);
        teacher.setSelectNumber(10);
        teacher.setHaveSelectedNumber(0);
        teacher.setRole(Role.TEACHAER);

        teacherBaseRepository.save(teacher);

        Student student = new Student();
        student.setNumber(2017214216);
        student.setName("张师原");
        student.setRole(Role.STUDENT);
        studentBaseRepository.save(student);

        Course course = new Course();
        course.setName("web框架");
        course.setValue(2.0F);
        course.setTeacher(teacher);
       courseBaseRepository.save(course);

        Direction direction = new Direction();
        direction.setName("java后端");
        directionBaseRepository.save(direction);

        TeacherDirection teacherDirection = new TeacherDirection();
        teacherDirection.setTeacher(teacher);
        teacherDirection.setDirection(direction);

        teacherDirectionBaseRepository.save(teacherDirection);

        StudentDirection areaStudent = new StudentDirection();
        areaStudent.setStudent(student);
        areaStudent.setDirection(direction);
        studentDirectionBaseRepository.save(areaStudent);

        StudentCourse courseStudent = new StudentCourse();
        courseStudent.setStudent(student);
        courseStudent.setCourse(course);
        courseStudent.setGrade(95.0F);
        studentCourseBaseRepository.save(courseStudent);

    }
}