package com.app.t2k.service.init.impl;

import com.app.t2k.kafka.to.elastic.config.KafkaConfigData;
import com.app.t2k.kafka.to.elastic.kafka.admin.config.client.KafkaAdminClient;
import com.app.t2k.service.init.StreamInitializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author t0k02w6 on 22/06/21
 * @project event-driven-microservice
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaStreamInitializer implements StreamInitializer {
    private final KafkaConfigData kafkaConfigData;
    private final KafkaAdminClient kafkaAdminClient;

    @Override
    public void init() {
        try {
            kafkaAdminClient.createTopics();
            kafkaAdminClient.checkSchemaRegistry();
            log.info("Topic with the name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
        } catch (Exception ex) {
            log.error("An error occurred ", ex);
        }
    }
}
