package com.app.t2k.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author t0k02w6 on 24/06/21
 * @project event-driven-microservice
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "kafka-consumer-config")
public class KafkaConsumerConfigData {
    private String keyDeserializer;
    private String valueDeserializer;
    private String consumerGroupId;
    private String autoOffsetReset;
    private String specificAvroReaderKey;
    private String specificAvroReader;
    private Boolean batchListener;
    private Boolean autoStartup;
    private Integer concurrencyLevel;
    private Integer sessionTimeoutMs;
    private Integer heartbeatIntervalMs;
    private Integer maxPollIntervalMs;
    private Integer maxPartitionFetchBytesDefault;
    private Integer maxPartitionFetchBytesBoostFactor;
    private Long pollTimeoutMs;
    private Long maxPollRecords;

}
