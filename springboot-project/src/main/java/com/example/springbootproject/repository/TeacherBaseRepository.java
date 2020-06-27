package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Teacher;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherBaseRepository extends BaseRepository<Teacher,Integer> {

    @Query("FROM Teacher t where t.id=:id")
    Optional<Teacher> findTeacherById(@Param("id") Integer id);

    @Query("from Teacher t where t.name =:name")
    Optional<List<Teacher>> findTeacherByName(@Param("name") String name);

    @Query("from Teacher t where t.number=:num")
    Optional<Teacher> findTeacherByNumber(@Param("num")Integer num);

    @Modifying
    @Query("UPDATE Teacher t set t.haveSelectedNumber=:num where t.id=:id")
    void updateHaveSelectedNumber(@Param("num") Integer num,@Param("id")Integer id);

    @Modifying
    @Query("UPDATE Teacher teacher SET teacher.password=:password where teacher.id=:id")
    void updatePassword(@Param("password")String password,@Param("id") Integer id);

    @Modifying
    @Query("update Teacher teacher set teacher.selectNumber=:selectNumber where teacher.id=:id")
    void updateSelectNumber(@Param("selectNumber")Integer selectNumber,@Param("id") Integer id);


    @Modifying
    @Query("UPDATE Teacher t set t.ranges=:ranges where t.id=:id")
    void updateRanges(@Param("ranges")Integer ranges,@Param("id")Integer id);


}