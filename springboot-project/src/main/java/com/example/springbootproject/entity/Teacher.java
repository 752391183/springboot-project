package com.example.springbootproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"students","teacherDirections","courses"})
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer number;//员工号
    private String name;
    private Integer selectNumber;//可以选择学生的数量
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //序列化时忽略这个属性，反序列化正常
    private String password ;//登陆密码，设置初始密码，可修改
    private Integer haveSelectedNumber;//已经选择学生的数量
    private Integer ranges;//选择范围，前多少名
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;
    @OneToMany(mappedBy = "teacher")
    private List<Student> students;
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
    @OneToMany(mappedBy = "teacher")
    private List<TeacherDirection> teacherDirections;


    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp"+
            " on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;

    //添加teacher基于id的构造函数，以便通过id直接创建teacher对象
     public Teacher (Integer id) {
         this.id = id;
     }
}