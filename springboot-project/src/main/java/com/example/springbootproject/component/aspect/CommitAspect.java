package com.example.springbootproject.component.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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

