package com.example.springbootproject.service;

import com.example.springbootproject.entity.Students;
import com.example.springbootproject.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequirementsService {
    @Autowired
    private RequirementsService requirementsService;
    @Autowired
    private EntityService entityService;

    /*
     提前内定一个学生，教师可选择学生数量减1
    */
    public Students AdavanceElected(Students students,Teacher teacher){
       entityService.updateTeacher(students.getId(),teacher);
       entityService.updateSelectNumber(teacher.getId(),1);
       return entityService.getStudents(students.getId());
    }

}
