package com.example.bosstrain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart implements Serializable {

    //商家的Id,唯一值
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer sellerId;
    //商家的名称
    private String sellerName;
    //存储多个订单详情
    @OneToMany(mappedBy = "cart")
    private List<OrderItem> orderItemList;

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
