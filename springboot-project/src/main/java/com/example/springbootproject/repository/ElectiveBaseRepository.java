package com.example.springbootproject.repository;

import com.example.springbootproject.entity.Elective;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectiveBaseRepository extends BaseRepository<Elective,Integer> {
}