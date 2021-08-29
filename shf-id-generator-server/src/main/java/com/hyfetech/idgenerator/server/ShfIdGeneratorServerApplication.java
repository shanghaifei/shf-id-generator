package com.hyfetech.idgenerator.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 尚海沸ID生成器服务
 * @author shanghaifei
 * @date 2021/8/25
 */
@ComponentScan(value = "com.hyfetech.idgenerator")
@SpringBootApplication
public class ShfIdGeneratorServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShfIdGeneratorServerApplication.class);
    }
}
