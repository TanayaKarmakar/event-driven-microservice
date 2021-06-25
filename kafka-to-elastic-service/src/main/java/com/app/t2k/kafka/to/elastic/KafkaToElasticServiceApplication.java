package com.app.t2k.kafka.to.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author t0k02w6 on 24/06/21
 * @project event-driven-microservice
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.app.t2k")
public class KafkaToElasticServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaToElasticServiceApplication.class, args);
    }
}
