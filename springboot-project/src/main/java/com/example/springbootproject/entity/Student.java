package com.example.springbootproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"studentCourses", "studentDirections"}) //序列化忽略类中的字段
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Integer number;//学号
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;//密码
    private String name;
    private Boolean whetherSelected;//是否被老师选
    private Integer sore;//专业排名
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)// 序列化时忽略这个属性，反序列化无影响
    private Role role;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "student")
    private List<StudentCourse> studentCourses ; //学生与课程之间的联系
    @OneToMany(mappedBy = "student")
    private List<StudentDirection> studentDirections;//学生与方向之间的联系


    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp"+
            " on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;

    //添加student基于id的构造函数，以便通过id直接创建student对象
    public Student(Integer id) {
        this.id = id;
    }

}