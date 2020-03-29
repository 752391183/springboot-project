package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Directions;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionsBaseRepository extends BaseRepository<Directions,Integer> {
}