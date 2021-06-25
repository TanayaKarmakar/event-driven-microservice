package com.app.t2k.kafka.to.elastic.service.consumer.impl;

import com.app.t2k.config.KafkaConfigData;
import com.app.t2k.kafka.admin.config.client.KafkaAdminClient;
import com.app.t2k.kafka.avro.model.TwitterAvroModel;
import com.app.t2k.kafka.to.elastic.service.consumer.KafkaConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author t0k02w6 on 24/06/21
 * @project event-driven-microservice
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TwitterKafkaConsumer implements KafkaConsumer<Long, TwitterAvroModel> {
    private final KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
    private final KafkaAdminClient kafkaAdminClient;
    private final KafkaConfigData kafkaConfigData;

    @Override
    @KafkaListener(id = "twitterTopicListener", topics = "${kafka-config.topic-name}")
    public void receive(@Payload List<TwitterAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of messages received with keys {}, partitions {} and " +
                "offsets {}, sending it to elastic: Thread Id {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString(),
                Thread.currentThread().getId());

    }
}
