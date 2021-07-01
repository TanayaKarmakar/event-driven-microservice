package com.app.t2k.kafka.to.elastic.transformer;

import com.app.t2k.elastic.model.index.impl.TwitterIndexModel;
import com.app.t2k.kafka.avro.model.TwitterAvroModel;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author t0k02w6 on 27/06/21
 * @project event-driven-microservice
 */
@Component
public class AvroToElasticModelTransformer {
    public List<TwitterIndexModel> getElasticModels(List<TwitterAvroModel> avroModels) {
        return avroModels.stream()
                .map(avroModel -> TwitterIndexModel.builder()
                        .useId(avroModel.getUserId())
                        .id(String.valueOf(avroModel.getId()))
                        .text(avroModel.getText())
                        .createdAt(ZonedDateTime.ofInstant(Instant.ofEpochMilli(Long.valueOf(avroModel.getCreatedAt())),
                                ZoneId.systemDefault()))
                        .build()
                ).collect(Collectors.toList());
    }
}
