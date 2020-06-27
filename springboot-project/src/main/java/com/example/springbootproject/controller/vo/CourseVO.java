package com.example.springbootproject.controller.vo;

import com.example.springbootproject.entity.Course;
import com.example.springbootproject.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class CourseVO {
    private Course course;
    private List<Student> students;
}
