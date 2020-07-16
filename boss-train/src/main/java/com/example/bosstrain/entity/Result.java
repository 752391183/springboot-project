package com.example.bosstrain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
  返回前端的json数据
 */
@NoArgsConstructor
@Data
public class Result implements Serializable {

    //返回ture/false
    private boolean success;
    //返回信息
    private String message;
    //返回数据
    private Object data;

    //失败时候返回的信息
   public Result(boolean success,String message) {
       this.success = success;
       this.message = message;
   }
   //成果时候返回的信息
   public Result(boolean success,String message,Object data) {
       this.success = success;
       this.message = message;
       this.data = data;
   }

}
