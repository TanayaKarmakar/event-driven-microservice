package com.app.t2k.service.transformer;

import com.app.t2k.kafka.to.elastic.kafka.avro.model.TwitterAvroModel;
import org.springframework.stereotype.Component;
import twitter4j.Status;

/**
 * @author t0k02w6 on 22/06/21
 * @project event-driven-microservice
 */
@Component
public class TwitterStatusToAvroTransformer {
    public TwitterAvroModel getTwitterAvroModelFromStatus(Status status) {
        return TwitterAvroModel.newBuilder()
                .setId(status.getId())
                .setUserId(status.getUser().getId())
                .setText(status.getText())
                .setCreatedAt(String.valueOf(status.getCreatedAt().getTime()))
                .build();
    }
}
