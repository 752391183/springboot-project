package com.example.springbootproject.controller;

import com.example.springbootproject.component.EncryptComponent;
import com.example.springbootproject.component.RequestComponent;
import com.example.springbootproject.entity.Course;
import com.example.springbootproject.entity.Student;
import com.example.springbootproject.entity.Teacher;
import com.example.springbootproject.service.StudentService;
import com.example.springbootproject.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/teacher/")
public class TeacherController {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    //登录页面
    @GetMapping("welcome")
    public void getIndex() {
          log.debug("{}",requestComponent.getid());
    }

    @GetMapping("index")
    public Map getTeacher() {
        Teacher t = teacherService.getTeacherById(requestComponent.getid());
        return Map.of(
                "teacher" ,t,
                "courses",t.getCourses(),
                "students",t.getStudents()
        );
    }

    @PostMapping("courses")
    public Map postCourse(@RequestBody Course course) {
        teacherService.addCourse(course,requestComponent.getid());
        return Map.of("course",course);
    }

    @PostMapping("password")
    public void postPassword(@RequestBody Map<String,String> data) {
        teacherService.updatePassword(requestComponent.getid(),data.get("password"));
       // return Map.of("teacher",teacherService.getTeacherById(requestComponent.getid()));
    }

    @PostMapping("selectNumber")
    public Map postSelectNumber(@RequestBody Map<String,Integer> data) {
        teacherService.updateSelectNumber(data.get("selectNumber"),requestComponent.getid());
        return Map.of("teacher",teacherService.getTeacherById(requestComponent.getid()));
    }

    @PostMapping("ranges")
    public Map postRanges(@RequestBody Map<String,Integer> data) {
        teacherService.updateRanges(requestComponent.getid(),data.get("ranges"));
        return Map.of("teacher",teacherService.getTeacherById(requestComponent.getid()));
    }
/*
    @PostMapping("advanceElected")
    public Map postAdvanceElected(@RequestBody Student student) {
        teacherService.AdvanceElected(student.getId(),requestComponent.getid());
        return Map.of("teacher",teacherService.getTeacherById(requestComponent.getid()));
    }
*/
    @PostMapping("student")
    public Map postStudents(@RequestBody Student student) {
        teacherService.addStudent(requestComponent.getid(),student);
        return Map.of("student",student);
    }
}
