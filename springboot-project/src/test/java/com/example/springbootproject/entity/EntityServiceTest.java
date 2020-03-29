package com.example.springbootproject.entity;

import com.example.springbootproject.service.EntityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
public class EntityServiceTest {
    @Autowired
    private EntityService entityService;
    @Test
    public void init(){
        Teacher tea1 = new Teacher();
        tea1.setName("王波教师");
        entityService.addTeacher(tea1);

        Students stu1 = new Students();
        stu1.setName("张师原");
        stu1.setId(2017214216);
        stu1.setTeacher(tea1);
        entityService.addStudents(stu1);

        Students stu2 = new Students();
        stu2.setName("张澎");
        stu2.setId(2017214215);
        stu2.setTeacher(tea1);
        entityService.addStudents(stu2);

        Course cou1 = new Course();
        cou1.setName("JAVA");
        entityService.addCourse(cou1);

        Course cou2 = new Course();
        cou2.setName("Web框架");
        entityService.addCourse(cou2);

        Directions dir1 = new Directions();
        dir1.setName("方向1");
        entityService.addDirections(dir1);

        Directions dir2 = new Directions();
        dir2.setName("方向2");
        entityService.addDirections(dir2);

        DirectionsElective dirEle1 = new DirectionsElective();
        dirEle1.setDetail("方向与学生之间的关系1");
        dirEle1.setStudents(stu1);
        dirEle1.setDirections(dir2);
        entityService.addDirectionsElective(dirEle1);

        Elective ele1 = new Elective();
        ele1.setDetail("学生和课程之间的关系");
        ele1.setCourse(cou1);
        ele1.setStudents(stu1);
        entityService.addElective(ele1);
    }

}
