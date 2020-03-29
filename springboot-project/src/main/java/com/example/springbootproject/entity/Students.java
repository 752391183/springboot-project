package com.example.springbootproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Students {
    @Id
    private int id;
    private String name;

    private Boolean whetherSelected;//是否被老师选中

    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp"+
            " on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "students")
    private List<Elective> electives ; //学生与课程之间的联系

    @OneToMany(mappedBy = "students")
    private List<DirectionsElective> directionsElectives;//学生与方向之间的联系



}