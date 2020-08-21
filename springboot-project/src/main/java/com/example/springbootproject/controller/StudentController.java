package com.example.springbootproject.controller;

import com.example.springbootproject.component.RequestComponent;
import com.example.springbootproject.entity.Direction;
import com.example.springbootproject.entity.Student;
import com.example.springbootproject.entity.Teacher;
import com.example.springbootproject.service.CourseService;
import com.example.springbootproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student/")
public class StudentController {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;


    @GetMapping("index")
    public Map getIndex() {
        Student student = studentService.getStudentById(requestComponent.getid());
        return Map.of(
                "student",student,
                "directions",courseService.listDirections()
        );
    }
    @GetMapping("directionByTeacherId/{teacherId}")
    public Map getDirections(@PathVariable Integer tid) {
        return Map.of("directions",courseService.listDirectionsByTeacherId(tid));
    }

    @PostMapping("directionStudent")
    public Map choseDirections(@RequestBody Map<String, List<Integer>> list) {
        List<Integer> integers = list.get("directions");
        List<Direction> directions = studentService.choseDirection(requestComponent.getid(),integers);
        return Map.of("directiosn",directions);
    }

    @PatchMapping("student")
    public Map choseStudent(@RequestBody Map<String,Integer> map) {
        Integer teacherId = map.get("teacherId");
        Teacher teacher = studentService.choseTeacher(requestComponent.getid(),teacherId);
        return Map.of("teacher",teacher);
    }
}
