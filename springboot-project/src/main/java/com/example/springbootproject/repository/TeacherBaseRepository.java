package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Teacher;
import javafx.beans.property.adapter.JavaBeanProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherBaseRepository extends BaseRepository<Teacher,Integer> {
    @Modifying
    @Query("UPDATE Teacher teacher SET teacher.password=:password where teacher.id=:id")
    int updatePassword(@Param("password")String password,@Param("id") int id);

    @Modifying
    @Query("update Teacher teacher set teacher.selectNumber=:selectNumber where teacher.id=:id")
    int updateSelectNumber(@Param("selectNumber")int selectNumber,@Param("id") int id);
}