package com.example.demo_3.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.example.demo_3")
@SpringBootApplication
@MapperScan("com.example.demo_3.mapper")
@EnableScheduling
public class Demo3Application {

    private static final Logger LOG = LoggerFactory.getLogger(Demo3Application.class);
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Demo3Application.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("地址: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}
