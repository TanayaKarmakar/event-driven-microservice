package com.app.t2k.kafka.to.elastic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author t0k02w6 on 26/06/21
 * @project event-driven-microservice
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "elastic-config")
public class ElasticConfigData {
    private String indexName;
    private String connectionUrl;
    private Integer connectionTimeoutMs;
    private Integer socketTimeoutMs;

}
