package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseBaseRepository extends BaseRepository<Course,Integer> {
}