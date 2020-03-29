package com.example.springbootproject.service;

import com.example.springbootproject.entity.*;
import com.example.springbootproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional

public class EntityService {
    @Autowired
    private StudentsBaseRepository studentsBaseRepository;
    @Autowired
    private CourseBaseRepository courseBaseRepository;
    @Autowired
    private TeacherBaseRepository teacherBaseRepository;
    @Autowired
    private ElectiveBaseRepository electiveBaseRepository;
    @Autowired
    private DirectionsBaseRepository directionsBaseRepository;
    @Autowired
    private DirectionsElectiveBaseRepository directionsElectiveBaseRepository;
    @Autowired
    private EntityService entityService;

    //Students
     public Students addStudents(Students students){

        studentsBaseRepository.save(students);
        return students;

    }
    public Students getStudents(int id){

         return studentsBaseRepository.findById(id).orElse(null);
    }
   public Students updateTeacher(int id,Teacher teacher){
         studentsBaseRepository.updateTeacher(teacher,id);
         return entityService.getStudents(id);
   }

    //Course
    public Course addCourse(Course course){

        courseBaseRepository.save(course);
        return course;

    }
    public Course getCourse(int id){

        return courseBaseRepository.findById(id).orElse(null);
    }


    //Teacher
    public Teacher addTeacher(Teacher teacher){

        teacherBaseRepository.save(teacher);
        return teacher;
    }
    public Teacher getTeacher(int id){

        return teacherBaseRepository.findById(id).orElse(null);

    }
    public Teacher updatePassword(int id,String password){
        teacherBaseRepository.updatePassword(password,id);
        return entityService.getTeacher(id);

    }

    public Teacher updateSelectNumber(int tID,int addNumber){
         int selectNumber = entityService.getTeacher(tID).getSelectNumber()-addNumber;
         teacherBaseRepository.updateSelectNumber(selectNumber,tID);
         return entityService.getTeacher(tID);
    }

    //Directions
    public Directions addDirections(Directions directions){

        directionsBaseRepository.save(directions);
        return directions;
    }
    public Directions getDirections(int id){

        return directionsBaseRepository.findById(id).orElse(null);
    }


    //DirectionsElective
    public DirectionsElective addDirectionsElective(DirectionsElective directionsElective){

        directionsElectiveBaseRepository.save(directionsElective);
        return directionsElective;
    }
    public DirectionsElective getDirectionsElective(int id){

        return directionsElectiveBaseRepository.findById(id).orElse(null);

    }


   //Elective
    public Elective addElective(Elective elective){

        electiveBaseRepository.save(elective);
        return elective;
    }

    public Elective getEletive(int id){

        return electiveBaseRepository.findById(id).orElse(null);

    }



}