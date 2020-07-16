package com.example.bosstrain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*
由于JPA原生的Repository接口没有实现refresh方法（从数据库强制拉取数据）
 通过自定义接口声明refresh方法，然后实现
 */
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {
    T refresh(T t);
}
