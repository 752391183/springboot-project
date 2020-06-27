package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Student;
import com.example.springbootproject.entity.StudentDirection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentDirectionBaseRepository extends BaseRepository<StudentDirection,Integer> {
     @Query("select d.student  from StudentDirection d where d.direction=:id ")
    Optional<List<Student>> listStudentByDirectionId(@Param("id") Integer id);
}