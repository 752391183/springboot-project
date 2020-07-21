package com.example.bosstrain.repository;

import com.example.bosstrain.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User,Integer> {

    //查找指定number的用户
    @Query("from User u where u.number =:number")
    Optional<User> findUserByNumber(@Param("number") Integer number);

}
