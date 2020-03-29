package com.example.springbootproject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class DirectionsElective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String detail;//具体的信息

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
    private Students students;
    @ManyToOne
    private Directions directions;

}