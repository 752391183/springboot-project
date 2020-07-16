package com.example.bosstrain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int count;
    @ManyToOne
    private Cart cart;

    //时间戳
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
