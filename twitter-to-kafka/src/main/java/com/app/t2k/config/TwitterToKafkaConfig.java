package com.app.t2k.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author t0k02w6 on 19/06/21
 * @project event-driven-microservice
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "twitter-to-kafka")
public class TwitterToKafkaConfig {
    private List<String> twitterKeywords;
}
