package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Direction;
import com.example.springbootproject.entity.TeacherDirection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherDirectionBaseRepository extends BaseRepository<TeacherDirection,Integer> {
    @Query("select td.direction from TeacherDirection td where td.teacher.id=:id")
    Optional<List<Direction>> listDirectionsByTeacherId(@Param("id") Integer id);
}
