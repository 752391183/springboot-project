package com.example.springbootproject.service;

import com.example.springbootproject.entity.*;
import com.example.springbootproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseBaseRepository courseBaseRepository;
    @Autowired
    private DirectionBaseRepository directionBaseRepository;
    @Autowired
    private StudentCourseBaseRepository studentCourseBaseRepository;
    @Autowired
    private TeacherDirectionBaseRepository teacherDirectionBaseRepository;
    @Autowired
    private StudentDirectionBaseRepository studentDirectionBaseRepository;

    //-----------课程的CRUD-------
    public Course addCourse(Course course) {
        return courseBaseRepository.save(course);
    }

    public void deleteCourse(Integer cid) {
        courseBaseRepository.deleteById(cid);
    }

    //基于id
    public Course getCourseById(Integer cid) {
        return courseBaseRepository.findById(cid).orElse(null);
    }

    //基于name
    public List<Course> getCourseByName(String name) {
        return courseBaseRepository.seletByName(name).orElse(List.of());
    }

    public Course updateCourse(Course course, Integer id) {
        courseBaseRepository.save(course);
        return course;
    }

    //获得全部课程
    public List<Course> listCourses() {
        return courseBaseRepository.findAll();
    }
    //------------------------------


    //--------------方向的CRUD-------------
    public Direction addDirection(Direction direction) {
        return directionBaseRepository.save(direction);
    }

    public void deleteDirection(Integer did) {
        directionBaseRepository.deleteById(did);
    }

    //基于id
    public Direction getDirectionById(Integer did) {
        return directionBaseRepository.findById(did).orElse(null);
    }

    //基于name
    public List<Direction> getDirectionByName(String name) {
        return directionBaseRepository.selectDirectionByName(name).orElse(List.of());
    }

    public Direction updateDirection(Direction direction) {
        directionBaseRepository.save(direction);
        return direction;
    }

    //获得全部方向
    public List<Direction> listDirections() {
        return directionBaseRepository.findAll();
    }

    public List<Direction> listDirectionsByTeacherId(Integer id) {
        return teacherDirectionBaseRepository.listDirectionsByTeacherId(id).orElse(List.of());
    }
    //-----------------------------------------

    //-------------StudentCourse------------------------
    public StudentCourse addStudentCourse(StudentCourse studentCourse) {
        return studentCourseBaseRepository.save(studentCourse);
    }

    public void deleteStudentCourse(Integer id) {
        studentCourseBaseRepository.deleteById(id);
    }

    public StudentCourse getStudentCourseById(Integer id) {
        return studentCourseBaseRepository.findById(id).orElse(null);
    }

    public StudentCourse updateStudentCourse(StudentCourse studentCourse) {
        return studentCourseBaseRepository.save(studentCourse);
    }
    //--------------------------------------------------------

    //----------------------StudentDirection----------------
    public StudentDirection addStudentDirection(StudentDirection studentDirection) {
        return studentDirectionBaseRepository.save(studentDirection);
    }

    public void deleteStudentDirection(Integer id) {
        studentDirectionBaseRepository.deleteById(id);
    }

    public StudentDirection getStudentDirectionById(Integer id) {
        return studentDirectionBaseRepository.findById(id).orElse(null);
    }

    public StudentDirection updateStudentDirection(StudentDirection studentDirection) {
        studentDirectionBaseRepository.save(studentDirection);
        return studentDirection;
    }
    //-------------------------------------------------------------

    //----------------------------TeacherDirection------------
    public TeacherDirection addTeacherDirection(TeacherDirection teacherDirection) {
        return teacherDirectionBaseRepository.save(teacherDirection);
    }

    public void deleteTeacherDirection(Integer id) {
        teacherDirectionBaseRepository.deleteById(id);
    }

    public TeacherDirection getTeacherDirectionById(Integer id) {
        return teacherDirectionBaseRepository.findById(id).orElse(null);
    }

    public TeacherDirection updateTeacherDirection(TeacherDirection teacherDirection) {
        teacherDirectionBaseRepository.save(teacherDirection);
        return teacherDirection;
    }
    //------------------------------------------------------------

}
