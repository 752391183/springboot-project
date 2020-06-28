package com.example.springbootproject.service;

import com.example.springbootproject.entity.*;
import com.example.springbootproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class StudentService {
    @Autowired
    private StudentBaseRepository studentBaseRepository;
    @Autowired
    private CourseBaseRepository courseBaseRepository;
    @Autowired
    private TeacherBaseRepository teacherBaseRepository;
    @Autowired
    private StudentCourseBaseRepository studentCourseBaseRepository;
    @Autowired
    private DirectionBaseRepository directionBaseRepository;
    @Autowired
    private StudentDirectionBaseRepository studentDirectionBaseRepository;
    @Autowired
    private TeacherService teacherService;


    // ---------学生CRUD-----------
    public Student addStudent(Student student) {
        return studentBaseRepository.save(student);
    }

    //指定id
    public Student getStudentById(Integer sid) {
        return studentBaseRepository.selectStudentById(sid).orElse(null);
    }
    //指定学号
    public Student getStudentByNumber(Integer number) {
        return studentBaseRepository.selectStudentByNumber(number).orElse(null);
    }
    //指定姓名
    public List<Student> getStudentByName(String name) {
        return studentBaseRepository.selectStudentByName(name).orElse(List.of());
    }
    public void deleteStudent(Integer sid) {
        studentBaseRepository.deleteById(sid);
    }
    public Student updateStudent(Student student) {
        studentBaseRepository.save(student);
        return student;
    }

   //------------------------------

    //获取全部学生

    public List<Student> listStudents() {
        return studentBaseRepository.findAllByOrderBySoreDesc().orElse(List.of());
    }
/*
    public Page<Student> listStudents() {
        return studentBaseRepository.list(PageRequest.of(0,20,Sort.Direction.DESC));
    }
*/
    //获取指定老师已选取的学生
    public List<Student> listStudents(Integer tid) {
        return studentBaseRepository.selectStudentByTeacherId(tid).orElse(List.of());
    }

    //获取选取了指定方向的学生
    public List<Student> listStudentsByDirection(Integer did) {
        
       return studentDirectionBaseRepository.listStudentByDirectionId(did).orElse(List.of());
    }

    //选择方向
    public List<Direction> choseDirection(Integer sid,List<Integer> directionIds) {
        List<Direction> directions = new ArrayList<>();
        for(Integer d : directionIds) {
            StudentDirection studentDirection = new StudentDirection();
            studentDirection.setStudent(getStudentById(sid));
            studentDirection.setDirection(directionBaseRepository.findById(d)
                    .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND,"您输入的课设方向不存在"))
            );
            studentDirectionBaseRepository.save(studentDirection);
            directions.add(studentDirection.getDirection());
        }
         return directions;
    }

    //选择老师
    public Teacher choseTeacher(Integer sid,Integer tid) {
        Student student = getStudentById(sid);
        Teacher teacher = teacherService.getTeacherById(tid);
        if (student.getTeacher()!= null) {
            Teacher t = teacherService.getTeacherById(student.getTeacher().getId());
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"您已经是 "+ t.getName()+" 的学生了,不能再选择别的老师");
        }
        if (teacherService.preScreen(sid,teacher)!= null) {
            student.setTeacher(teacher);
            teacher.setHaveSelectedNumber(teacher.getHaveSelectedNumber()+1);
            return teacher;
        }else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"当前老师人数已满");

        }
    }



    //获取排名
    /*
    public int getStudentSore(Integer sid) {
        List<Student> students = listStudents();
        Student student = getStudentById(sid);
        int sore = 0;
        for(Student s : students) {

        }
    }
 */
/*
    //3.能否选取老师
    public Boolean toSelectTeacher(Teacher teacher,Integer sid) {
        int num = teacher.getNumber()-teacher.getHaveSelectedNumber();
        if (num>0 && teacher.getRanges()>getStudentById(sid).getSore()){
            return true;
        }
        return false;
    }



    //4.学生导师互选成功(将学生whetherSelected设置为true，同时将老师的haveSeletedNumber+1；)
    public Student suceedSelectTeacher(Teacher teacher,Integer sid) {


    }

 */
}