package com.app.t2k.service.listener;

import com.app.t2k.kafka.to.elastic.config.KafkaConfigData;
import com.app.t2k.kafka.to.elastic.kafka.avro.model.TwitterAvroModel;
import com.app.t2k.kafka.to.elastic.kafka.producer.config.service.KafkaProducer;
import com.app.t2k.service.transformer.TwitterStatusToAvroTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.StatusAdapter;

/**
 * @author t0k02w6 on 19/06/21
 * @project event-driven-microservice
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterKafkaStatusListener extends StatusAdapter {
    private final KafkaConfigData kafkaConfigData;
    private final KafkaProducer<Long, TwitterAvroModel> kafkaProducer;
    private final TwitterStatusToAvroTransformer twitterStatusToAvroTransformer;

    @Override
    public void onStatus(Status status) {
        log.info("Twitter status with text: {}", status.getText());
        TwitterAvroModel twitterAvroModel = twitterStatusToAvroTransformer.getTwitterAvroModelFromStatus(status);
        kafkaProducer.send(kafkaConfigData.getTopicName(), twitterAvroModel.getUserId(), twitterAvroModel);
    }
}
