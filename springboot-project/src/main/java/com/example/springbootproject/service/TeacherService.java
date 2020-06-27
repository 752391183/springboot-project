package com.example.springbootproject.service;

import com.example.springbootproject.component.EncryptComponent;
import com.example.springbootproject.entity.Course;
import com.example.springbootproject.entity.Role;
import com.example.springbootproject.entity.Student;
import com.example.springbootproject.entity.Teacher;
import com.example.springbootproject.repository.CourseBaseRepository;
import com.example.springbootproject.repository.StudentBaseRepository;
import com.example.springbootproject.repository.TeacherBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentBaseRepository studentBaseRepository;
    @Autowired
    private TeacherBaseRepository teacherBaseRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PasswordEncoder encoder;

   // private ArrayList<Map<Integer,Integer>> list = new ArrayList<>();


    //-----------Teacher CRD----------
    public Teacher addTeacher(Teacher teacher) {
        return teacherBaseRepository.save(teacher);
    }
    public void  deleteTeacher(Integer id) {
        teacherBaseRepository.deleteById(id);
    }
    //指定id
    public Teacher getTeacherById(Integer tid) {
        return teacherBaseRepository.findTeacherById(tid).orElse(null);
    }
    //指定number
    public Teacher getTeacherByNumber(Integer number) {
        return teacherBaseRepository.findTeacherByNumber(number).orElse(null);
    }
   //指定姓名
    public List<Teacher> getTeacherByName(String name) {
        return teacherBaseRepository.findTeacherByName(name).orElse(List.of());
    }
    //------------------------------------------

    //获取全部的老师
    public List<Teacher>  listTeachers() {
        return teacherBaseRepository.findAll();
    }



    //对申请的学生进行系统初步筛选，筛选通过后返回学生信息给老师（学生模拟声明一个sore属性）

    public Student preScreen(Integer sid, Teacher teacher) {
         Student s = studentService.getStudentById(sid);
         int num = teacher.getSelectNumber() - teacher.getHaveSelectedNumber();
           if(num>0){
               if (teacher.getRanges()>s.getSore()) {
                   return s;//返回学生信息给老师，老师需要通过确认才表示同意成为其导师
               }
               else{
                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"您的综合成绩不满足该老师的要求，" +
                         "如果需要选择该老师请直接和老师联系");
               }
           }
           else {
               throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"该老师已达到可接受学生的最大值，请尽快联系别的老师");
           }

    }

    //老师对通过系统筛选的学生进行确认接受操作
    public Teacher toGetStudent(Integer tid,Student student) {

        addStudent(tid,student);
        return getTeacherById(tid);
    }
    //老师对通过系统筛选的学生进行拒绝接受操作
    public String toNotGetStudent(Integer tid,Student student) {

        return "由于某些原因，该老师拒绝了您的请求，请尽快选择别的导师";
    }

     //为指定教师添加一门课程
    public Teacher addCourse(Course course,Integer tid) {
        Teacher t = teacherBaseRepository.findTeacherById(tid).orElse(null);
        course.setTeacher(t);
       courseService.addCourse(course);
       return t;
    }

    /**
     * 教师提前内定一个学生
     * @param student
     * @param tid
     * @return
     */
/*
    public Student AdvanceElected(Student student, Integer tid) {
        if (studentService.toSelectTeacher(getTeacherById(tid), student.getId())) {

            Student s = Optional.ofNullable(studentService.getStudentByNumber(student.getNumber()))
                     .orElseGet(() -> {

                     })
        }
    }

 */
    /**
     * 获取互选成功的所有学生信息
     * @param tid
     * @return
     */

    public List<Student> getAllSelectedStudents(Integer tid) {

        return  teacherBaseRepository.findTeacherById(tid).orElse(null).getStudents();
    }

    //老师修改密码
    public Teacher updatePassword(Integer tid,String newPassword) {
        newPassword = encoder.encode(newPassword);
       teacherBaseRepository.updatePassword(newPassword,tid);
       return teacherBaseRepository.findTeacherById(tid).orElse(null);
    }

    //更新可选学生人数
    public Teacher updateSelectNumber(Integer newNumber,Integer tid){
        teacherBaseRepository.updateSelectNumber(newNumber,tid);
        return teacherBaseRepository.findTeacherById(tid).orElse(null);
    }

    //老师修改选择范围(前多少名）
    public Teacher updateRanges(Integer tid,Integer ranges) {
        teacherBaseRepository.updateRanges(ranges,tid);
        return teacherBaseRepository.findTeacherById(tid).orElse(null);
    }

    //给指定老师添加一名学生，如果学生在数据库中则直接添加，如果不存则创建
    public Student addStudent(Integer tid,Student student) {
        Student s = Optional.ofNullable(studentService.getStudentByNumber(student.getNumber()))
                 .orElseGet(() -> {
                     student.setPassword(encoder.encode(String.valueOf(student.getNumber())));
                     student.setRole(Role.STUDENT);
                     student.setWhetherSelected(true);
                     student.setTeacher(new Teacher(tid));
                     studentService.addStudent(student);
                     //studentBaseRepository.refresh(student);
                     return  student;
                 });
        s.setTeacher(new Teacher(tid));
       // studentService.addStudent(s);
        //studentBaseRepository.refresh(s);
        return s;
    }
/*
    //4.学生导师互选成功(将学生whetherSelected设置为true，同时将老师的haveSeletedNumber+1；)
    public Boolean suceedSelectTeacher(Student student,Integer tid) {
            studentBaseRepository.updateWhetherSelected(true, student);
            teacherBaseRepository.updateHaveSelectedNumber(number,tid);
            return true;

        return false;
    }

 */

}
