package com.security.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 【author：chenglonghy , QQ：983956409】
 * @return
 * @date 2020/5/1 16:11
 * @history 2020/5/1 16:11 【author：chenglonghy , QQ：983956409】  create.
 */
@MapperScan(basePackages = {"com.security.demo.dao"})
@SpringBootApplication
public class DemoApplication {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
