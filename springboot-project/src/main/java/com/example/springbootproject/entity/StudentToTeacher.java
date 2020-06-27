package com.example.springbootproject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
/*
  用于处理学生对老师的多次请求，存储第一次学生对老师的请求
 */
@JsonIgnoreProperties({"student","teacher"})
public class StudentToTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Student student;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Teacher teacher;

}
