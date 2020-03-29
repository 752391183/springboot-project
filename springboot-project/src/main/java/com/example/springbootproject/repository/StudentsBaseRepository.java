package com.example.springbootproject.repository;


import com.example.springbootproject.entity.Students;
import com.example.springbootproject.entity.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsBaseRepository extends BaseRepository<Students,Integer> {
    @Modifying
    @Query("UPDATE Students students SET students.teacher=:teacher where students.id=:id")
    int updateTeacher(@Param("teacher")Teacher teacher,@Param("id")int id);

}