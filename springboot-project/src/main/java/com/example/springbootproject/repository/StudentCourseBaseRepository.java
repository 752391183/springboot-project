package com.example.springbootproject.repository;

import com.example.springbootproject.entity.StudentCourse;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseBaseRepository extends BaseRepository<StudentCourse,Integer> {

}