package com.example.springbootproject.controller;

import com.example.springbootproject.component.RequestComponent;
import com.example.springbootproject.entity.Teacher;
import com.example.springbootproject.service.CourseService;
import com.example.springbootproject.service.StudentService;
import com.example.springbootproject.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/root/")
public class RootController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private RequestComponent requestComponent;

    @GetMapping("teahchers")
    public Map getTeachers() {
        return Map.of("teachers",teacherService.listTeachers());
    }

    @GetMapping("index")
    public Map getIndex() {
        return Map.of(
                "teachers",teacherService.listTeachers(),
                "students",studentService.listStudents(),
                "courses",courseService.listCourses(),
                "directions",courseService.listDirections()
        );
    }

    @PostMapping("teacher")
    public Map postTeacher(@RequestBody Map<String,String> tea) {
        log.debug("{}",tea);
        Teacher t = new Teacher();
        t.setPassword(encoder.encode(tea.get("password")));
        t.setNumber(Integer.valueOf(tea.get("number")));
        t.setName(tea.get("name"));
        t.setHaveSelectedNumber(Integer.valueOf(tea.get("haveSelectedNuber")));
        t.setSelectNumber(Integer.valueOf(tea.get("selectNumber")));
        t.setRanges(Integer.valueOf(tea.get("ranges")));
        teacherService.addTeacher(t);
        return Map.of("teacher",t);
    }

    @PatchMapping("teacher")
    public Map patchTeacher(@RequestBody Map<String,Teacher> map) {
       Teacher tea = teacherService.updateTeacher(map.get("teacher"),requestComponent.getid());
        return Map.of("teacher",tea);
    }

    @DeleteMapping("teacher/{tid}")
    public void deleteTeacher(@PathVariable Integer tid) {
       teacherService.deleteTeacher(tid);

    }
}
