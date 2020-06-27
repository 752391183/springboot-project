package com.example.springbootproject.service;

import com.example.springbootproject.entity.Course;
import com.example.springbootproject.entity.Direction;
import com.example.springbootproject.entity.Student;
import com.example.springbootproject.entity.Teacher;
import com.example.springbootproject.repository.CourseBaseRepository;
import com.example.springbootproject.repository.DirectionBaseRepository;
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

    //-----------课程的CRD-------
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
    //------------------------------

    //获得全部课程
    public List<Course> listCourses() {
        return courseBaseRepository.findAll();
    }


    //--------------方向的CRD-------------
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
   //-----------------------------------------

   //获得全部方向
   public List<Direction> listDirections() {
        return directionBaseRepository.findAll();
   }

}
