package com.example.bosstrain;

import com.example.bosstrain.repository.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)  // 声明使用自己定义的Repository而不是Jpa自带的接口
public class BossTrainApplication {

    public static void main(String[] args) {
        SpringApplication.run(BossTrainApplication.class, args);
    }

}
