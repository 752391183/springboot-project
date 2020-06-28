package com.example.springbootproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"studentDirections","teacherDirections"})
public class Direction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "direction")
    private List<StudentDirection> studentDirections;
    @OneToMany(mappedBy = "direction")
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
}