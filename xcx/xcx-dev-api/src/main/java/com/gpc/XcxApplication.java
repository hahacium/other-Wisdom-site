package com.gpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"com.gpc" , "org.n3r.idworker"})
@MapperScan(basePackages = "com.gpc.mapper")
public class XcxApplication {

    public static void main(String[] args) {
        SpringApplication.run(XcxApplication.class, args);
    }

}
