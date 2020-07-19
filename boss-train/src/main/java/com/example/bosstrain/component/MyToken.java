package com.example.bosstrain.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyToken {
    public static final String AUTHORIZATION = "Authorization";
    public static final String UID = "uid";
    private Integer uid;
}

