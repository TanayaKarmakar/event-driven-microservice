package com.app.t2k.elastic.query.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author t0k02w6 on 02/07/21
 * @project event-driven-microservice
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.app.t2k")
public class ElasticQueryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticQueryServiceApplication.class, args);
    }
}

