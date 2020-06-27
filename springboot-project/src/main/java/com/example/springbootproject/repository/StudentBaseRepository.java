package com.example.springbootproject.repository;


import com.example.springbootproject.entity.Student;
import com.example.springbootproject.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentBaseRepository extends BaseRepository<Student,Integer> {
    @Query("from Student s where s.id=:id")
    Optional<Student> selectStudentById(@Param("id") Integer id);

    @Query("from Student s where s.number=:number")
    Optional<Student> selectStudentByNumber(@Param("number") Integer number);

    @Query("from Student s where s.name=:name")
    Optional<List<Student>> selectStudentByName(@Param("name") String name);

    //@Query("from Student s ")
    //Page<Student> list(Pageable pageable);

    Optional<List<Student>> findAllByOrderBySoreDesc() ;

    @Query("from Student s where s.teacher.id=:tid")
    Optional<List<Student>> selectStudentByTeacherId(@Param("tid")Integer tid);

    @Modifying
    @Query("UPDATE Student s SET s.teacher.id=:tid where s.id=:sid")
    void updateTeacher(@Param("tid")Integer tid,@Param("sid") Integer sid);

    @Modifying
    @Query("UPDATE Student s SET s.whetherSelected=:str where s.id=:id")
    void updateWhetherSelected(@Param("str") Boolean str,@Param("id") Integer id);
}