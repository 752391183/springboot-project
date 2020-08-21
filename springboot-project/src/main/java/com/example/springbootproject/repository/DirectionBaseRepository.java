package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionBaseRepository extends BaseRepository<Direction,Integer> {
    @Query("from Direction d where d.name =:name")
    Optional<List<Direction>> selectDirectionByName(@Param("name")String name);

}