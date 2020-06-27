package com.example.springbootproject.component;

import com.example.springbootproject.entity.Role;
import com.example.springbootproject.entity.Teacher;
import com.example.springbootproject.service.TeacherService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/*
  初始化组件，添加管理员,仅在系统第一次运行的时候启动
 */
@Component
public class InitComponent implements InitializingBean {

    @Autowired
    private TeacherService teacherService;
    //初始化数据存入数据库的时候就该对密码进行编码
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        final int num = 2017;
        /*
        当程序运行的时候，判断是否已经存在（可能程序第一次运行已经创建，而出于某些原因又给项目停止了
        再运行的时候还是算第一次运行
         */
        Teacher t = teacherService.getTeacherByNumber(num);
        if (t == null) {
            Teacher t1 = new Teacher();
            t1.setName("BO");
            t1.setNumber(num);
            t1.setPassword(encoder.encode(String.valueOf(num)));
            t1.setRole(Role.ROOT);
            t1.setRanges(25);
            t1.setSelectNumber(10);
            t1.setHaveSelectedNumber(0);
            teacherService.addTeacher(t1);
        }
    }
}
