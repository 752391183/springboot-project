package com.example.springbootproject.component.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/*
  用于学生多次请求同一个老师时候的处理方法，声明切面去切preScreen（）方法
   在执行这个方法前，先进入数据库中拿到StudentToTeacher该学生选择过的老师对应的主键
    从而判断该学生是否选取过该老师，以免重复向老师放请求成为老师的请求
 */
@Aspect
@Component
public class CommitAspect {

    @Pointcut("execution(* com.example.springbootproject.service.TeacherService.preScreen(..))")
    public void pointcut(){};

    @Around("pointcut()")
    public void Around(ProceedingJoinPoint joinPoint) {
        Object[] obj = joinPoint.getArgs();

    }

}

